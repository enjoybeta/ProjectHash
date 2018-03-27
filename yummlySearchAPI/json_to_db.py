import cassandra
import sys
import json
from cassandra.cluster import Cluster
import os

'''python program used to add data to database from a specific folder that contains json files of recipe data'''
if __name__ == '__main__':
	#change to target data folder
	os.chdir("./detailedData")

	#create database connection
	cluster = Cluster()
	#use keyspace 'hash'
	session = cluster.connect('hash')

	#iterate through each json file in the folder
	for root, dirs, files in os.walk("."):
		for filename in files:

			#load and extract the data
			data = json.load(open(filename))
			recipeid = data["id"]
			recipename = data["name"] 
			totaltime = data["totalTimeInSeconds"]
			if "hostedLargeUrl" in data["images"][0]:
				image = data["images"][0]["hostedLargeUrl"]
			else:
				image = None
			ingredients = data["ingredientLines"]
			number = data["numberOfServings"]
			largest = 0
			main_flavor = ""
			for flavor in data["flavors"].keys():
				if data["flavors"][flavor] > largest:
					largest = data["flavors"][flavor]
					main_flavor = flavor
			instruction = data["source"]["sourceRecipeUrl"]

			#insert data into database
			session.execute(
							'''
							INSERT INTO public_recipe (id, name, time, imageurl, ingredients, numberofserving, flavor, instruction)
							VALUES (%s, %s, %s, %s, %s, %s, %s, %s)
							''',
							(recipeid, recipename, totaltime, image, ingredients, number, main_flavor, instruction)
						)

	#close database connection
	cluster.shutdown()
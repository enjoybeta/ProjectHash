import cassandra
import sys
import json
from cassandra.cluster import Cluster

if __name__ == '__main__':
	cluster = Cluster()
	session = cluster.connect('hash')
	data = json.load(open('recipe.json'))
	recipeid = data["id"]
	recipename = data["name"] 
	totaltime = data["totalTimeInSeconds"]
	image = data["images"][0]["hostedLargeUrl"]
	ingredients = data["ingredientLines"]
	number = data["numberOfServings"]
	largest = 0
	main_flavor = ""
	for flavor in data["flavors"].keys():
		if data["flavors"][flavor] > largest:
			largest = data["flavors"][flavor]
			main_flavor = flavor
	#flavor = data["flavors"]
	instruction = data["source"]["sourceRecipeUrl"]
	session.execute(
					'''
					INSERT INTO public_recipe (id, name, time, imageurl, ingredients, numberofserving, flavor, instruction)
					VALUES (%s, %s, %s, %s, %s, %s, %s, %s)
					''',
					(recipeid, recipename, totaltime, image, ingredients, number, main_flavor, instruction)
				)
	cluster.shutdown()
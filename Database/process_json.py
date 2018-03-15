import sys
import json
import cassandra
from cassandra.cluster import Cluster
from pprint import pprint

'''data = json.load(open('recipe.json'))
for line in data["ingredientLines"]:
	pprint(line)
return_data = { 'name': data["name"],
				'id': data["id"],
				'ingredientLines': data["ingredientLines"],
				'totaltime': data["totalTimeInSeconds"],
				'numberofserving': data["numberOfServings"],
				'imageURLs': data["images"],
				'flavors': data["flavors"]}
with open('return.json', 'w') as outfile:
	json.dump(return_data, outfile)'''

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
instruction = data["source"]["sourceRecipeUrl"]
return_data = { 'name': recipename,
				'id': recipeid,
				'ingredient': ingredients,
				'totaltime': totaltime,
				'numberofserving': number,
				'imageURL': image,
				'flavor': main_flavor}
with open('return.json', 'w') as outfile:
	json.dump(return_data, outfile)
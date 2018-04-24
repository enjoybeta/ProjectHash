import sys
import json
import cassandra
from cassandra.cluster import Cluster
import random

''' python function to randomly choose four recipes from database for the homepage recommandation'''
def random_today():
	#create connection to database
	cluster = Cluster()
	session = cluster.connect('hash')
	rows = session.execute('''
				SELECT * from public_recipe
				'''
			)
	#format the result data from database
	recipe_list = []
	for row in rows:
		recipe_data = { 'name': row.name,
						'id': row.id,
						'ingredientLines': row.ingredients,
						'totaltime': row.time,
						'numberofserving': row.numberofserving,
						'imageURLs': row.imageurl,
						'flavor': row.flavor,
						'instructionurl': row.instruction}
		recipe_list.append(recipe_data)
	return_list = []
	#randomly choose four recipes
	while len(return_list) < 4:
		random_recipe = random.choice(recipe_list)
		#avoid duplicate and recipes taht doesn't have image
		if random_recipe not in return_list and random_recipe['imageURLs'] is not None:
			return_list.append(random_recipe)
	json_return = json.dumps(return_list)
	#close database connection
	cluster.shutdown()
	return json_return

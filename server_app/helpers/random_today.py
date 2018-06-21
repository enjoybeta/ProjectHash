import sys
import json
import cassandra
from cassandra.cluster import Cluster
import random
import datetime

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
	#set the day of year as the seed for random shuffle
	seed = int(datetime.date.today().strftime("%j"))
	random.seed(seed)
	#shuffle the list
	random.shuffle(recipe_list)
	#add four recipes to return list
	i = 0
	while len(return_list) < 4:
		#avoid duplicate and recipes taht doesn't have image
		if recipe_list[i] not in return_list and recipe_list[i]['imageURLs'] is not None:
			return_list.append(recipe_list[i])
		i += 1
	json_return = json.dumps(return_list)
	#close database connection
	cluster.shutdown()
	return json_return

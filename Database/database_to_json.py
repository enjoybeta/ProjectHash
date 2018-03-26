import sys
import json
import cassandra
from cassandra.cluster import Cluster
from pprint import pprint

'''sample python program to simulate situation that return json file based on a search in database'''
if __name__ == '__main__':
	#create connection to database
	cluster = Cluster()
	session = cluster.connect('hash')

	#specific a recipe to search in database
	id = "French-Onion-Soup-2141595"
	rows = session.execute('''
				SELECT * from public_recipe where id = %s
				''',
				(id,)
			)
	#format the result data from database
	for row in rows:
		return_data = { 'name': row.name,
						'id': row.id,
						'ingredientLines': row.ingredients,
						'totaltime': row.time,
						'numberofserving': row.numberofserving,
						'imageURLs': row.imageurl,
						'flavor': row.flavor,
						'instructionurl': row.instruction}
		#write the data into a json file
		with open('return2.json', 'w') as outfile:
			json.dump(return_data, outfile)
	#close database connection
	cluster.shutdown()
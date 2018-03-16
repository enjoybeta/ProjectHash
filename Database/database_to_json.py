import sys
import json
import cassandra
from cassandra.cluster import Cluster
from pprint import pprint


if __name__ == '__main__':
	cluster = Cluster()
	session = cluster.connect('hash')
	id = "French-Onion-Soup-2141595"
	rows = session.execute('''
				SELECT * from public_recipe where id = %s
				''',
				(id,)
			)
	for row in rows:
		#return_data = row
		return_data = { 'name': row.name,
						'id': row.id,
						'ingredientLines': row.ingredients,
						'totaltime': row.time,
						'numberofserving': row.numberofserving,
						'imageURLs': row.imageurl,
						'flavor': row.flavor,
						'instructionurl': row.instruction}
		with open('return2.json', 'w') as outfile:
			json.dump(return_data, outfile)
		#print return_data
	cluster.shutdown()
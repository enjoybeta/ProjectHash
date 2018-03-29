import sys
import json
import cassandra
from cassandra.cluster import Cluster
from pprint import pprint

'''
	python function that return json file based on a numberofserving search in database
   	@request:  json object of input data
'''
def search_serving(request):
	#create connection to database
	cluster = Cluster()
	session = cluster.connect('hash')

	#decode input data
	data = json.loads(request.decode('utf-8'))
	num = data["numberofserving"]
	#excute cql to get result recipes
	rows = session.execute('''
				SELECT * from public_recipe where numberofserving = %s limit 10 allow filtering
				''',
				(num,)
			)
	#format the result data from database
	return_list = []
	for row in rows:
		return_data = { 'name': row.name,
						'id': row.id,
						'ingredientLines': row.ingredients,
						'totaltime': row.time,
						'numberofserving': row.numberofserving,
						'imageURLs': row.imageurl,
						'flavor': row.flavor,
						'instructionurl': row.instruction}
		return_list.append(return_data)
	#encode the result data into json object
	json_return = json.dumps(return_list)
	return json_return
	#close database connection
	cluster.shutdown()

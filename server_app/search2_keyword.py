import sys
import json
import cassandra
from cassandra.cluster import Cluster


'''
	python function that return json file based on a keyword search in database
    @request: json object of input data (keyword)
'''
def search_keyword(request):
	#create connection to database
	cluster = Cluster()
	session = cluster.connect('hash')

	#decode input data
	data = json.loads(request.decode('utf-8'))
	keyword = data["keyword"]
	#excute cql to get result recipes
	rows = session.execute('''
				SELECT * from public_recipe where name like '%''' + keyword + '''%' limit 10 allow filtering
				'''
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
	#close database connection
	cluster.shutdown()
	return json_return

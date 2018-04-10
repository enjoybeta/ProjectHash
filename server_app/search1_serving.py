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
	h_ingredients = data["having"]
	n_ingredients = data["not having"]
	#excute cql to get result recipes
	rows = session.execute('''
				SELECT * from public_recipe where numberofserving = %s allow filtering
				''',
				(num,)
			)
	#format the result data from database
	return_buff = []
	count = 0
	for row in rows:
		buff = []
		for h_ingre in h_ingredients:
			for ingredient in row.ingredients:
				if h_ingre in ingredient:
					count += 1
					break
		for n_ingre in n_ingredients:
			for ingredient in row.ingredients:
				if n_ingre in ingredient:
					count = 0
					break
		if float(count) / len(row.ingredients) > 0.01 :
			return_data = { 'name': row.name,
							'id': row.id,
							'ingredientLines': row.ingredients,
							'totaltime': row.time,
							'numberofserving': row.numberofserving,
							'imageURLs': row.imageurl,
							'flavor': row.flavor,
							'instructionurl': row.instruction}
			buff.append(return_data)
			buff.append(float(count) / len(row.ingredients))
		#write the data into a json file
			return_buff.append(buff)
		count = 0
	return_buff = sorted(return_buff, key = lambda buff: buff[1], reverse = True)
	return_list = []
	for i in range(min(10, len(return_buff))):
		return_list.append(return_buff[i][0])
	#encode the result data into json object
	json_return = json.dumps(return_list)
	return json_return
	#close database connection
	cluster.shutdown()

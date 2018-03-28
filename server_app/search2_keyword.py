import sys
import json
import cassandra
from cassandra.cluster import Cluster
from pprint import pprint

'''sample python function to simulate situation that return json file based on a search in database'''
def search_keyword(request):
	#create connection to database
	cluster = Cluster()
	session = cluster.connect('hash')
	data = json.loads(request.decode('utf-8'))
	keyword = data["keyword"]
	rows = session.execute('''
				SELECT * from public_recipe where name like '%''' + keyword + '''%' allow filtering
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
		#write the data into a json file
		#name = row.id + '.json'
		return_list.append(return_data)
	#with open("search1_return.json", 'w') as outfile:
	json_return = json.dumps(return_list)
	return json_return
	#close database connection
	cluster.shutdown()

#if __name__ == "__main__":
#    search_keyword('search2_request.json')

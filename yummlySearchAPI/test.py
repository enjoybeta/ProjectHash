import urllib.request, urllib.parse, json

class YummlySearch:
    def __init__(self, appId_, appKey_):
        self.baseSearchAddress = 'http://api.yummly.com/v1/api/recipes?'
        self.baseRecipeDetailAddress = 'http://api.yummly.com/v1/api/recipe/'
        self.appId = appId_
        self.appKey = appKey_

    def generateSearchRequest(self, keyWords):
        if (not keyWords):
            raise RuntimeError('Key words cannot be empty')
        queryWord = keyWords[0]
        for i in range(1, len(keyWords)):
            queryWord += '+' + keyWords[i]
        parameter = {'_app_id': self.appId, '_app_key': self.appKey, 'q': queryWord}
        return self.baseSearchAddress + urllib.parse.urlencode(parameter)

    def getResult(self, searchRequest, requestTimeout = 10):
        return json.loads(urllib.request.urlopen(searchRequest, timeout = requestTimeout).read())

    def generateRecipeDetailRequest(self, recipeId):
        parameter = {'_app_id': self.appId, '_app_key': self.appKey}
        return self.baseRecipeDetailAddress + recipeId + '?' + urllib.parse.urlencode(parameter)

def saveJsonToFile(json, fileName):
    with open(fileName, 'w') as output:
        output.write(json.dumps(json, indent = 4))

if __name__ == '__main__':
    appId = '4a4d16bf'
    appKey = 'c60f961fefede4811a9f5e9709cb6829'
    search = YummlySearch(appId, appKey)
    searchRequest = search.generateRecipeDetailRequest('French-Onion-Soup-2141595')
    saveJsonToFile(search.getResult(searchRequest), 'test_data_recipe.json')
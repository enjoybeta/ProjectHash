import urllib.request, urllib.parse, json

class YummlySearch:
    def __init__(self, baseSearchAddress_ = 'http://api.yummly.com/v1/api/recipes?', baseRecipeDetailAddress_ = 'http://api.yummly.com/v1/api/recipe/', appId_ = '4a4d16bf', appKey_ = 'c60f961fefede4811a9f5e9709cb6829'):
        self.baseSearchAddress = baseSearchAddress_
        self.baseRecipeDetailAddress = baseRecipeDetailAddress_
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

class RecipeSearchResult:
    pass

class Recipe:
    def __init__(self):
        pass

if __name__ == '__main__':
    search = YummlySearch()
    searchRequest = search.generateSearchRequest(['onion', 'soup'])
    print(searchRequest)
    searchRequest = search.generateRecipeDetailRequest('French-Onion-Soup-2141595')
    print(searchRequest)
    with open('test_data_recipe.json', 'w') as output:
        output.write(json.dumps(search.getResult(searchRequest), indent = 4))
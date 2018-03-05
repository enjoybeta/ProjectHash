import urllib.request, urllib.parse, json, collections, os

debug = True

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

def saveJsonToFile(jsonDict, fileName):
    output = open(fileName, 'w')
    output.write(json.dumps(jsonDict, indent = 4))
    output.close()

def getIdsFromSearchResult(jsonDict):
    recipeIds = []
    for recipe in jsonDict['matches']:
        recipeIds.append(recipe['id'])
    return recipeIds

def getIngredientFromSearchResult(jsonDict):
    ingredients = set()
    for recipe in jsonDict['matches']:
        for ingredient in recipe['ingredients']:
            ingredients.add(ingredient)
    return ingredients

def BFS(searchAPI, startIngredient, resultFoler, searchResultFolder, recipeCountLimitation):
    visitedId = set()
    visitedIngredient = set()
    fringe = collections.deque()
    fringe.append(startIngredient)
    recipeCounter = 0
    while (recipeCounter < recipeCountLimitation):
        if (not fringe):
            print('Fringe empty, search terminated')
            return
        top = fringe.popleft()
        if (top in visitedIngredient):
            continue
        visitedIngredient.add(top)
        if (debug):
            print('Expanding ' + top + ' as search keyword')
        searchResult = searchAPI.getResult(searchAPI.generateSearchRequest(top))
        saveJsonToFile(searchResult, '.' + os.sep + searchResultFolder + os.sep + top)
        ids = getIdsFromSearchResult(searchResult)
        for i in ids:
            if (i in visitedId):
                continue
            print('Trying to save information about id: ' + i)
            recipeCounter += 1
            recipeDetail = searchAPI.getResult(searchAPI.generateRecipeDetailRequest(i))
            saveJsonToFile(recipeDetail, '.' + os.sep + resultFoler + os.sep + i)
            visitedId.add(i)
        ingredients = getIngredientFromSearchResult(searchResult)
        for ingredient in ingredients:
            fringe.append(ingredient)

if __name__ == '__main__':
    appId = '4a4d16bf'
    appKey = 'c60f961fefede4811a9f5e9709cb6829'
    search = YummlySearch(appId, appKey)
    BFS(search, 'salt', 'detailedData', 'searchResult', 10)
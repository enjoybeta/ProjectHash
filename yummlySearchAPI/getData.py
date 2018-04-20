import urllib.request, urllib.parse, json, collections, os, time

debug = True
retryLimit = 20

# Search API class simulate the search interface
class YummlySearch:
    def __init__(self, appId_, appKey_):
        self.baseSearchAddress = 'http://api.yummly.com/v1/api/recipes?'
        self.baseRecipeDetailAddress = 'http://api.yummly.com/v1/api/recipe/'
        self.appId = appId_
        self.appKey = appKey_

    # Two types of request
    def generateSearchRequest(self, keyWords):
        if (not keyWords):
            raise RuntimeError('Key words cannot be empty')
        queryWord = keyWords[0]
        for i in range(1, len(keyWords)):
            queryWord += '+' + keyWords[i]
        parameter = {'_app_id': self.appId, '_app_key': self.appKey, 'q': queryWord}
        return self.baseSearchAddress + urllib.parse.urlencode(parameter)

    # After get the request, get the result from API use this function
    def getResult(self, searchRequest, requestTimeout = 10):
        return json.loads(urllib.request.urlopen(searchRequest, timeout = requestTimeout).read())

    def generateRecipeDetailRequest(self, recipeId):
        parameter = {'_app_id': self.appId, '_app_key': self.appKey}
        return self.baseRecipeDetailAddress + recipeId + '?' + urllib.parse.urlencode(parameter)

# Save the result to file with fileName
def saveJsonToFile(jsonDict, fileName):
    output = open(fileName, 'w')
    output.write(json.dumps(jsonDict, indent = 4))
    output.close()

# Get id of the search result
def getIdsFromSearchResult(jsonDict):
    recipeIds = []
    for recipe in jsonDict['matches']:
        recipeIds.append(recipe['id'])
    return recipeIds

# Get ingredients to expend nodes
def getIngredientFromSearchResult(jsonDict):
    ingredients = set()
    for recipe in jsonDict['matches']:
        for ingredient in recipe['ingredients']:
            ingredients.add(ingredient)
    return ingredients

# The search process follows BFS start from an ingredient and expand node by ingredients
def BFS(searchAPI, startIngredient, resultFoler, searchResultFolder, recipeCountLimitation):
    # Record visited nodes to avoid duplicate
    visitedId = set()
    visitedIngredient = set()
    fringe = collections.deque()
    fringe.append(startIngredient)
    recipeCounter = 0
    # This count limit the search size to a specific number to avoid infinity loop
    while (recipeCounter < recipeCountLimitation):
        # End of the search
        if (not fringe):
            print('Fringe empty, search terminated')
            return
        top = fringe.popleft()
        if (top in visitedIngredient):
            continue
        visitedIngredient.add(top)
        # Debug print
        if (debug):
            print('Expanding ' + top + ' as search keyword')
        searchResult = None
        retryCount = 0
        # Retry the quest if failed
        while (retryCount < retryLimit):
            try:
                searchResult = searchAPI.getResult(searchAPI.generateSearchRequest(top))
            # Handle the error
            except urllib.error.HTTPError:
                retryCount += 1
                if (debug):
                    print('Fail to expand this node, retry after 5 seconds')
                time.sleep(5)
                continue
            break
        # Retry limitation exceed and still failed
        if (searchResult == None):
            raise RuntimeError('Retry Limitation reached')
        # Save the search result
        saveJsonToFile(searchResult, '.' + os.sep + searchResultFolder + os.sep + top)
        # Get recipes from search result
        ids = getIdsFromSearchResult(searchResult)
        for i in ids:
            # Pass visited
            if (i in visitedId):
                continue
            print('Trying to save information about id: ' + i)
            recipeCounter += 1
            retryCount = 0
            recipeDetail = None
            while (retryCount < retryLimit):
                try:
                    recipeDetail = searchAPI.getResult(searchAPI.generateRecipeDetailRequest(i))
                except urllib.error.HTTPError:
                    retryCount += 1
                    if (debug):
                        print('Fail to save this node, retry after 5 seconds')
                    time.sleep(5)
                    continue
                break
            if (recipeDetail == None):
                raise RuntimeError('Retry Limitation reached')
            # Save recipe details to file
            saveJsonToFile(recipeDetail, '.' + os.sep + resultFoler + os.sep + i)
            visitedId.add(i)
        ingredients = getIngredientFromSearchResult(searchResult)
        # Continue BFS
        for ingredient in ingredients:
            fringe.append(ingredient)

if __name__ == '__main__':
    # Id and key from yummly API
    appId = '4a4d16bf'
    appKey = 'c60f961fefede4811a9f5e9709cb6829'
    # Create API interface
    search = YummlySearch(appId, appKey)
    # Begin search start from beef
    BFS(search, 'beef', 'detailedData', 'searchResult', 1000)
## Case study - Dictionary App
First time working with Jetpack Compose, coroutines and some of the used framworks. Took this as a learning opportunity!

## Additional assumptions (not defined in case):
- No local storage/cash to persist data - data fetched from url again when app restarts i.e. all values restored also the deleted words. And data not shown when offline.﻿
- Error handling - different message on network errors, all other errors are undefined error.
- No requirement to continue loading data if app backgrounded.
- No requirements to support specific Android API versions, different countries/languages, screen sizes.
- No search/voice search button needed as in the example UI of the case.

## Some bugs/issues/improvement areas (I’m aware of and would fix if there was more time):
- List could be more user friendly, for ex. enable jumping to sections by letter
- Keyboard has to be dismissed manually (as there’s no search button)
- UI is simple and ugly
- Animations
- If user types before data is loaded, the full word list is shown when loaded, and the search result is displayed as soon as user types again
- Forgot to add empty line EOF in some files
- Is there any search/filter algoritm with better performance?

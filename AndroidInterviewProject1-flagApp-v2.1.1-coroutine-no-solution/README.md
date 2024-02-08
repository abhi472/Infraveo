# AndroidInterviewProject1

## Description:

Build a simple app that shows a list of countries from an API.
MainActivity - show list of countries
DetailActivity or DetailComposeActivity to show detail info of each country

https://docs.google.com/document/d/1uvl2ZFu20I_-8GNBe8Yj-uHQ4ZrAhzsxSkxS2HKodvY/edit?usp=sharing

## TO DO:
1. Phase 1
2. Implement MainActivity & MainViewModel
   - load data by using MainViewModel.loadData()
   - show list of countries
      - Portrait mode - 1 column
      - Landscape mode - 3 colums
      - scrollable if whole list does not fit on the screen
      - Refer to the below screenshots 
   - MainActivity.onItemClicked should
      - launch DetailActivity - xml view
      -  or
      - launch DetailComposeActivity - JetpackCompose 
3. Implement DetailActivity or DetailComposeActivity
   - ![Screen Shot 2023-11-28 at 9 46 47 AM](https://github.com/iheartradio/AndroidInterviewProject1/assets/532059/be92df5d-4c3b-4d2e-8cdf-b2f0a3e07787)
   - Activity actionbar name should be the country name
   - Show Country flag image on Left side
      - implement DetailViewModel.setImage to show correct flag
      - image name is *countryCode*.png  ex) https://ihrandroidtesting.s3.amazonaws.com/country/flag/png250px/us.png
   - Show Country.name on right side of the Flag
   - Show text view "Native name:" on right side of the Flag and below Country.name. "Native name:" should not be hardcoded but load from string.xml
   - Show Country.nativeName on right side of the Flag and below "Native name:" label
   - Show Bordered countries section
      - Show text view "2. Bordered countries: numOfCountries" on below the Flag. The text should not be hardcoded but load from string.xml
      - show the list of Country.borders: List<String>
   -  Add “BACK” button on the bottom to go back to previous screen
4. BONUS
   - Show Languages of the country section
      - Show text view "3. Official Languages:" on below "2. Bordered countries" section. The text should not be hardcoded but load from string.xml
         - show the list of Country.languages: List<Language>
         - Language.name + "::" + Language.nativeName
      - Refer to the below screenshots
      - DetailActivity or DetailComposeActivity should be scrollable if does not fit
   - for main activity, implement loading, empty & success states.
   - write unit test

### screenshot:
#### Main Activity - Portrait mode
#### - 1 column
#### ![ ](https://github.com/iheartradio/AndroidInterviewProject1/assets/532059/a723e4c5-90b7-4d86-8cc2-1b156edae37d)

#### Main Activity - Landscape mode
#### - 3 columns
#### ![Screen Shot 2023-11-28 at 7 32 21 AM](https://github.com/iheartradio/AndroidInterviewProject1/assets/532059/385792d8-dba3-4acf-b928-a7998c80d2c5)


#### Detail Activity - Portrait mode
#### ![Screen Shot 2023-11-28 at 7 43 34 AM](https://github.com/iheartradio/AndroidInterviewProject1/assets/532059/d27f550a-878f-4669-97fa-3d76d4b99826)
#### Detail Activity - Landscape mode
####  ![Screen Shot 2023-11-28 at 9 07 07 AM](https://github.com/iheartradio/AndroidInterviewProject1/assets/532059/7a1bee43-92ff-4cbd-a1fa-576eab597261)


 

# Android App - Tournal
 - An android app named Tournal wich helps people with archiving their vacation memories or their exciting trips!
 - Made with Java and XML.

## Contents
- Splash screen && main page
- Adding an item
- Adding an item to favorites
- Items description

## Screenshots and details

### Used elements
 - NavigationDrawer, RecyclerView, CardView, ConstraintLayout
 - MaterialDesign, Fragments
 - LiveData, Room, Retrofit

#### Splash screen && main page
- When first entering the app we have a splash screen wich has the app logo
- The main app is based on a navigation drawer
- The list of trips(the 3rd image) uses a Recycler View and the items are made with CardView
- We can press the FAB button to add a new item
- We can also long press on each item and an Edit view is opened
<p>
    <img src="https://i.imgur.com/oKsVRCu.png" width="220" height="500" />
    <img src="https://i.imgur.com/Iak1oPC.png" width="220" height="500" />
    <img src="https://i.imgur.com/H7HTXXx.png" width="220" height="500" />
</p> 

#### Adding an item
 - By pressing the FAB button we open an Add view
 - We can import images from gallery by pressing the select image button
 - When pressing start date or end date buttons an DatePickerDialog is shown
 - And if we press save we add an item to the Recycler View
<p>
    <img src="https://i.imgur.com/htvGqFG.png" width="220" height="500" />
    <img src="https://i.imgur.com/gh4TAyK.png" width="220" height="500" />
</p>

#### Adding an item to favorites
 - If we press the heart button, that item will be added to the favorite section
<p>
    <img src="https://i.imgur.com/hg4UC5A.png" width="220" height="500" />
    <img src="https://i.imgur.com/AXvuAvr.png" width="220" height="500" />
</p>

#### Items description
 - When we press an item we enter a description page
 - The weather info in the top right is displayed via an API using Retrofit
 - Here we can also delete the selected item
<p>
    <img src="https://i.imgur.com/4rObOEZ.png" width="220" height="500" />
</p>










# ARC (augmented reality commucicator)
(Branch:CurrentState holds current work)

To-Fix
* COMMENTS, make them better,more complete,make sense.

This is the ARC project, an augmented reality social networking app for android. It is part of my second year computer science coursework.

## Design
I have tried to have my app follow the MVP organisational principal; Android kind of does this inately but not (at least in my eyes) very well (Model=dataLogic, Presenter=Activities, View=XML). It strikes me that having: Model=dataLogic, Presenter=middleman, View=Activity, might yield more palatable results (I will probably live to eat those words, but hey ho).

Oh yes, and i should probably mention that ARC uses a framework called "Beyondar", the most important packages of which are Beyondar.android.Fragment and Beyondar.android.world. The former handles the Camera instance and the GLSurfacePreview(onto which the camera data is projected), while the latter deals with the initialisation, population and control of the augmented 'world': creating the world, adding objects to the world, setting coords for objects, setting images/names/etc of objects, etc, etc, etc.

So the key tasks left to me are:
* positioning
* manipulation of Beyondar library
* the rest of the features and functionality ;)

So going on my above premise below are my proposed responsibilities for the three entities:

### View
* Main activity
* Camera Fragment (beyondar)
* Map Fragment (beyondar)
* Post Fragment (write and submit a post)
* Controls Fragment (user controls)

### Presenter
* world presenter (get() from dataModel and update() camera/map fragment)
* controls presenter(updates and maintains appropriate user controls)
* data presenter(set() datamodel elements)
* plugin manager (sits between presenters and data model)
* plugin(s) (between manager and data model, adds auxilliry functionality)

### Data Model
* its the bloody data retrieval interface.

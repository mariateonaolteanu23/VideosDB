#Olteanu Maria-Teona 321CA

GENERAL INFO
Implementation uses a reflection based approach to factory design pattern.
This way, we separate the user from the details of the action's construction.

DATABASE
We load all input data related to videos, actors and users into a database.
Database can be accessed from everywhere by any action.

ACTIONS
Inheritance & dependencies:
All actions have a common inherited override execution method.
Each override method is implemented to perform a specific action.

Action 
- has 3 subclasses: Command, Query, Recommendation
- an abstract executeAction method

Command 
- has 3 subclasses: View, Rating, Favorite

Recommedation 
- has 5 subclasses: Standard, BestUnseen, Popular, Favorite, Search

Query  
- depends on ObjectQuery class

ObjectQuery
- has 4 subclasses: ActorsQuery, MoviesQuery, ShowsQuery, UsersQuery
Each ObjectQuery instance is dependent upon the query's criteria.

We have the following query criteria classes:
ActorsCriteria
- has 3 subclasses: ByAverage, ByAwards, ByFilerDescription

UsersCriteria
- has 1 subclass: ByNumRatings

VideosCriteria
- has 4 subclasses: ByLongest, ByRating, ByMostViewed, ByFavorite

Query action details
We retrieve the object and criteria classes through reflection.
Criteria clasess have a method that returns a list of filtered/sorted objects.
ObjectQuery classes methods receive those lists and covert them to strings.

FLOW
Application uses external action input to construct & execute different actions
Based on action_type and type we create new instances of the specified actions.
Every action is generated like "an order".
  
We define a factory per action.
Factories are responsible for determining what run-time action should be used.
Using reflection we retrieve an action class based on given action data.
(e.g. action_type = "command" & type = "view" => Command command = new View())

The factories operate with the following constraints:
- retrieve only known actions (existing actions)
- if class can't be found, the factory will catch an exception

We have generated the requested action instance.
Then we execute the action.(e.g. View.executeAction())

OUTPUT
After execution, each action returns an accomplishment message.
All output messages will be printed in a file.

*NOTE*
The implementation is impacted by the checker tool. 
Vmchecker doesn't recognize uninstantiated classes at runtime.
It can't load and find those classes definitions.(ClassNotFoundException)
Therefore, implementation using reflection doesn't work without some additions.

On local machine the code works perfectly without the following adaptation!

We added load methods for classes that were uninstatiated before runtime.
This way, the checker tool identifies the reflection retrieved classes.
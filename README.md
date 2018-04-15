### GET api/users
Gets all users

### POST api/users/{username}
Creates new user

### DELETE api/users/{username}
Deletes user by username

### GET api/boards/{username}
Gets boards of an user by username

### GET api/boards/{username}/{name}
Gets weather for locations of a user's board

### POST api/boards/{username}/{name}
Creates a board for a certain user with given name

### POST api/locations/add (with params in body)
Creates or adds location to board

### DELETE api/locations/delete
Deletes location from board

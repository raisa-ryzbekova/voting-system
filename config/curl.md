### DISH CONTROLLER
#### create dish
`curl -s -X POST -d '{"date":"2018-12-01","description":"Created dish","price":300}' -H 'Content-Type:application/json;charset=UTF-8' http://localhost:8080/voter/rest/admin/restaurants/100003/dishes --user admin@gmail.com:admin`
#### get dish
`curl -s http://localhost:8080/voter/rest/admin/restaurants/100003/dishes/100006 --user admin@gmail.com:admin`
#### get all dishes of restaurant by date
`curl -s http://localhost:8080/voter/rest/admin/restaurants/100003/dishes-by-date?date=2018-11-07 --user admin@gmail.com:admin`
#### get all dishes of restaurant
`curl -s http://localhost:8080/voter/rest/admin/restaurants/100003/dishes --user admin@gmail.com:admin`
#### update dish
`curl -s -X PUT -d '{"date":"2018-12-24", "description":"Updated dish", "price":500}' -H 'Content-Type: application/json' http://localhost:8080/voter/rest/admin/restaurants/100003/dishes/100006 --user admin@gmail.com:admin`
#### delete dish
`curl -s -X DELETE http://localhost:8080/voter/rest/admin/restaurants/100003/dishes/100006 --user admin@gmail.com:admin`

### RESTAURANT CONTROLLER
#### create restaurant
`curl -s -X POST -d '{"name":"Restaurant 5","phone":"222225","address":"Street 5","date":"2018-12-24"}' -H 'Content-Type:application/json;charset=UTF-8' http://localhost:8080/voter/rest/admin/restaurants --user admin@gmail.com:admin`
#### get restaurant
`curl -s http://localhost:8080/voter/rest/admin/restaurants/100003 --user admin@gmail.com:admin`
#### get restaurant with dishes by date
`curl -s http://localhost:8080/voter/rest/admin/restaurants/100003/by-date?date=2018-11-07 --user admin@gmail.com:admin`
`curl -s http://localhost:8080/voter/rest/profile/restaurants/100003/by-date?date=2018-11-07 --user user1@yandex.ru:password1`
#### get list of restaurants with dishes by date
`curl -s http://localhost:8080/voter/rest/admin/restaurants/by-date-with-dishes?date=2018-11-07 --user admin@gmail.com:admin`
`curl -s http://localhost:8080/voter/rest/profile/restaurants/by-date-with-dishes?date=2018-11-07 --user user1@yandex.ru:password1`
#### get all restaurants
`curl -s http://localhost:8080/voter/rest/admin/restaurants --user admin@gmail.com:admin`
#### update restaurant
`curl -s -X PUT -d '{"name":"Updated restaurant","phone":"222225","address":"Street 5","date":"2018-12-24"}' -H 'Content-Type: application/json' http://localhost:8080/voter/rest/admin/restaurants/100003 --user admin@gmail.com:admin`
#### delete restaurant
`curl -s -X DELETE http://localhost:8080/voter/rest/admin/restaurants/100004 --user admin@gmail.com:admin`

### USER CONTROLLER
#### create user
`curl -s -X POST -d '{"name":"User3","email":"user3@yandex.ru","password":"password3"}' -H 'Content-Type:application/json;charset=UTF-8' http://localhost:8080/voter/rest/admin/users --user admin@gmail.com:admin`
#### get user
`curl -s http://localhost:8080/voter/rest/admin/users/100000 --user admin@gmail.com:admin`
`curl -s http://localhost:8080/voter/rest/profile --user user1@yandex.ru:password1`
#### get user by email
`curl -s http://localhost:8080/voter/rest/admin/users/by?email=user1@yandex.ru --user admin@gmail.com:admin`
#### get user with votes
`curl -s http://localhost:8080/voter/rest/profile/votes --user user1@yandex.ru:password1`
#### get all users
`curl -s http://localhost:8080/voter/rest/admin/users --user admin@gmail.com:admin`
#### update user
`curl -s -X PUT -d '{"name":"Update user","email":"user2@yandex.ru","password":"password2"}' -H 'Content-Type: application/json' http://localhost:8080/voter/rest/admin/users/100002 --user admin@gmail.com:admin`
#### enable
`curl -s -X POST -d '' http://localhost:8080/voter/rest/admin/users/100002?enabled=false --user admin@gmail.com:admin`
#### delete user
`curl -s -X DELETE http://localhost:8080/voter/rest/admin/users/100002 --user admin@gmail.com:admin`
`curl -s -X DELETE http://localhost:8080/voter/rest/profile --user user1@yandex.ru:password1`

### VOTE CONTROLLER
#### create vote
`curl -s -X POST -d '{"date":"2018-12-25","time":"10:00"}' -H 'Content-Type:application/json;charset=UTF-8' http://localhost:8080/voter/rest/profile/votes?restaurantId=100003 --user user1@yandex.ru:password1`
#### get vote
`curl -s http://localhost:8080/voter/rest/profile/votes/100017 --user user1@yandex.ru:password1`
#### get all votes of user with restaurants by date
`curl -s http://localhost:8080/voter/rest/admin/votes/votes-with-restaurants-by-date?date=2018-11-07 --user admin@gmail.com:admin`
#### get all votes of user
`curl -s http://localhost:8080/voter/rest/profile/votes --user user1@yandex.ru:password1`
#### update vote
`curl -s -X PUT -d '{"date":"2018-12-25","time":"10:30"}' -H 'Content-Type: application/json' http://localhost:8080/voter/rest/profile/votes/100020?restaurantId=100004 --user user1@yandex.ru:password1`
#### delete vote
`curl -s -X DELETE http://localhost:8080/voter/rest/profile/votes/100020 --user user1@yandex.ru:password1`
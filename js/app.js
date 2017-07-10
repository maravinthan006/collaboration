var app=angular.module("app",['ngRoute','ngCookies'])
app.config(function($routeProvider){
	$routeProvider
	.when('/register',{
		templateUrl:'_user/registerUser.html',
		controller:'UserController'
	})
	.when('/login',{
		templateUrl:'_user/login.html',
		controller:'UserController'
	})
	.when('/home',{
		templateUrl:'_home/home.html'
	})
	.when('/profilepic',{
		templateUrl:'_user/ProfilePic.html'
	})
    .when('/edituser',{
    	templateUrl:'_user/edituserform.html',
    	controller:'EditController'
    })
    
    .when('/jobpost',{
    	templateUrl:'job/jobform.html',
    	controller:'JobController'
    })
    
    .when('/getAllJobs',{
    	templateUrl:'job/getjobtitles.html',
    	controller:'JobController'
    })
    
    .when('/blogpost',{
    	templateUrl:'blog/blogform.html',
    	controller:'BlogController'
    })
    
    .when('/getAllBlogs',{
    	templateUrl:'blog/getBlogTitles.html',
    	controller:'BlogController'
    })
    .when('/getBlogDetail/:id',{
    	templateUrl:'blog/blogdetail.html',
    	controller:'BlogDetailController'
    })
    .when('/getBlogForApproval/:id',{
    	templateUrl:'blog/getBlogForApproval.html',
    	controller:'BlogDetailController'
    })
    .when('/getAllUsers',{
    	templateUrl:'friend/userslist.html',
    	controller:'FriendController'
    })
    .when('/pendingRequests',{
    	templateUrl:'friend/pendingrequests.html',
    	controller:'FriendController'
    })
    
    .when('/friendslist',{
    	templateUrl:'friend/listOfFriends.html',
    	controller:'FriendController'
    })
    
    .when('/chat',{
    	templateUrl:'chat/chat.html',
    	controller:'ChatController'
    })


})
app.run(function($rootScope,$cookieStore,UserService,$location){
	console.log('entering run method ')
	console.log($rootScope.currentUser)
	if($rootScope.currentUser==undefined){
		$rootScope.currentUser=$cookieStore.get("currentUser")
		console.log($rootScope.currentUser)
	}
	$rootScope.logout=function(){
		console.log('logout function')
		delete $rootScope.currentUser;
		$cookieStore.remove('currentUser')
		UserService.logout()
		.then(function(response){
			console.log("logged out successfully..");
			$rootScope.message="Loggedout Successfully"
			$location.path('/login')
		},
		function(response){
			console.log(response.status);
		})
	}
})
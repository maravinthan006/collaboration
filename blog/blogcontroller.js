app.controller("BlogController",function($scope,$location,BlogService){
	$scope.saveBlogPost=function(){
		BlogService.saveBlogPost($scope.blog)
		.then(function(response){
			$scope.successMessage="Your blog is posted successfully and it is waiting for approval from admin";
			$location.path('/getAllBlogs')
		},function(response){
			console.log(response.status)
			$location.path('/login')
		})
	}
	
	$scope.blogPosts=BlogService.getAllBlogs()
	.then(function(response){
		$scope.blogPosts=response.data;
		console.log(response.status)
	},function(response){
		console.log(response.status)
	})
	
	$scope.postsForApproval=BlogService.getAllBlogsForApproval()
	.then(function(response){
		$scope.postsForApproval=response.data;
	},function(response){
		console.log(response.status)
	})
	
})
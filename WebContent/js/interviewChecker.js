
/**checks if the user entered their first and last name**/
var app = angular.module('nameInfo', []);
app.controller('validateInfo', function($scope) {

    $scope.firstName = 'Johny';
    $scope.lastName = 'Bravo';
});

app.controller('validateInterview',function($scope){
	 $scope.answered=function(){
	
	    	var value=(document.getElementById("value").value); //gets how many questions are present 
	    	
	    	
	    	/**loops over all the checkboxes and verifies if they are indeed checked. If the checkbox(es) is/are checked, it will
	    	 * return true
	    	 */
	    	var i;    
	    	for(i=1;i <= value; i++){
	    		var answerNumber="answer"+i;//Variable with the value of answer(i), i being the iterator
	    		
	    	
	    		var answer=document.getElementById(answerNumber).value;//gets the value of answer i
	    		/**If one of the text areas is left blank it will return true which disables the submit button on the form**/
	    		
	    		if(answer==''){
	    			return true;
	    		}
	    		
	    	}
	    	/**reads the user input for the first and last name. If one of the fields is left blank or left with the default value
	    	 * the submit button will be disabled. Unfortunately, this means no person called Johny Bravo can complete an
	    	 * interview :(
	    	 */
	    	var firstName=document.getElementById("firstName").value;
	    	var lastName=document.getElementById("lastName").value;
	    	if(firstName==''||firstName=='Johny'||lastName=='Bravo'||lastName==''){
	    		return true;
	    	}
	    	
	    }
	   	
});



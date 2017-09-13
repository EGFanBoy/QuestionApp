var app = angular.module('checkApp', []);
app.controller('validateCheck', function($scope) {
    $scope.checkChecked=function(){
    	var value=(document.getElementById("value").value)-1; //gets how many questions are present 
    	
    	
    	/**loops over all the checkboxes and verifies if they are indeed checked. If the checkbox(es) is/are checked, it will
    	 * return true
    	 */
    	var i;    
    	for(i=1;i <= value; i++){
    		var checkNumber="check"+i;//Variable with the value of check(i), i being the iterator
    		var isChecked=document.getElementById(checkNumber).checked;//gets if the checkbox i is checked or not
    		if(isChecked!=false){
    			return true;
    		}
   
    		
    	}
    }
   
});
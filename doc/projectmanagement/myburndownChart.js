"use strict";


var addEditingButtons = function ()
{
    $("#updateButton").click(function(){
    	computeBurndown();
    });
}

var parseEffort = function(text)
{
    if (text.endsWith("h"))
	{
		return parseInt(text);
	}
	else if (text.endsWith("d"))
	{
		return 6 * parseInt(text);
	}
	else if (text.endsWith("w"))
	{
		return 24 * parseInt(text);
	}

	return parseInt(text);
}





var computeBurndown = function() 
{
	var mybody = $("body");
    var bodyKids = mybody.children();
    
    var estimated = 0;
    var timeSpent = 0;
	var done = false;
	var logList = [];
    
    for (var i = 0; i < bodyKids.length && ! done; i++)
    {
    	var current = bodyKids[i];
    	
    	var tagName = current.tagName;
    	
    	if (current.tagName == "DIV")
    	{
    		// look into it
    		var issueEstimated = {};
    		var issueRemain = 64000;
    		var issueTimeSpent = 0;
    		var logEntry = {};

    		for (var j = 0; j < current.children.length; j++) 
    		{
    			var divKid = current.children[j];

    			var text = divKid.innerText;

				if (text.startsWith("Estimated: "))
		    	{
		    		var duration = text.split(" ")[1];
		    		
		    		issueEstimated.estimated = parseEffort(duration);
		    	}
		    	else if (text.startsWith("Date:"))
		    	{
		    		var split = text.split(" ");
		    		var date = split[1];
		    		
		    		if (split.length > 2){
		    			date += " " + split[2];
		    		}


		    		logEntry.x = date;
		    	}
		    	else if (text.startsWith("Time spent:"))
		    	{
		    		var duration = text.split(" ")[2];
		    		
		    		logEntry.timeSpent = parseEffort(duration);
		    		issueTimeSpent += logEntry.timeSpent;
		    	}
		    	else if (text.startsWith("Remaining:"))
		    	{
		    		var duration = text.split(" ")[1];
		    		
		    		logEntry.remaining = parseEffort(duration);
		    		logEntry.issueEstimated = issueEstimated;

					logList.push(logEntry);
					logEntry = {};
		    	}

    		}

            estimated += issueEstimated.estimated;
    		timeSpent += issueTimeSpent;
    	}
    	else if (current.innerText.startsWith("Backlog:"))
        {
    		// stop summation
    		done = true;
        }
   	}

	var compareDate = function(a, b){
		var result = 0;
		if (a.x < b.x) result = -1;
		if (a.x > b.x) result = 1;
		return result
	} 


   	logList.sort(compareDate);
    	
    var result = "Estimated: " + estimated + " TimeSpent: " + timeSpent + " " + logList;
    	
    $("#output").text(result);	

    // compute myBurnDownData
    var myBurnDownData = [];
    var myTimeSpentData = [];

    var timeSpentSum = 0;
    var timeRemaining = estimated;

    for (var i = 0; i < logList.length; i++)
    {
    	var logEntry = logList[i];
    	
    	// first log entry
    	var timeDown = logEntry.issueEstimated.estimated - logEntry.remaining;
		    	 
    	timeRemaining -= timeDown;
		logEntry.issueEstimated.estimated -= timeDown; 

    	var burnEntry = {};
    	burnEntry.x = logEntry.x;
    	burnEntry.y = timeRemaining;

    	myBurnDownData.push(burnEntry);

		timeSpentSum += logEntry.timeSpent;
		var timeEntry = {};
		timeEntry.x = logEntry.x;
		timeEntry.y = timeSpentSum;

		myTimeSpentData.push(timeEntry);
    }


    var config = {
        type: 'line',
        data: {
            datasets: [{
                label: "Sprint BurnDown",
                data: myBurnDownData
            },
            {
                label: "Time Spent",
                data: myTimeSpentData
            },]
        },
        options: {
            // responsive: true,
            hover: {
                mode: 'single'
            },
            scales: {
                xAxes: [{
                	type: "time",
                	display: true,
                }],
            	yAxes: [{
                	display: true,
                	scaleLabel: {
                    	display: true,
                    	labelString: 'Value'
                	},
            		ticks: {
                    	suggestedMin: 0,
                	}                
            	}]
            }
        }
    };

   
	var ctx = document.getElementById("burndownChart");


	var myLineChart = new Chart(ctx, config);
};


var addButtonsAndChart = function () 
{
	addEditingButtons();

	computeBurndown();
}

window.onload = addButtonsAndChart;

// $(document).ready(addButtonsAndChart);



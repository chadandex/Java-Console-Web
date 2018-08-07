//Chad Andexler
//The Software Guild


$(document).ready(function () {
	$('#mainHeading').addClass('text-center');
	$('#get-weather').addClass('text-center');
	$('#weather-cond').addClass('text-center');

	$('#getWeather').on('click', function(){
		var haveValidationErrors = checkAndDisplayValidationErrors($('#get-weather').find('input'));
		var zipcode = $('#zipcode').val(), unit = $('#unit-choice').val();
		
		if(haveValidationErrors) {
			return false;
		}
		
		if(zipcode.length == 5){
			loadCurrent(zipcode, unit);
			loadWeather(zipcode, unit);
		} else {
			$('#errorMessages')
				.append($('<li>')
				.attr({class: 'list-group-item list-group-item-danger'})
				.text('Enter a five digit zipcode.'));
		}
		
	});
	
});

function loadCurrent(zip, unit) {
	clearWeather();
	
	var key = "773b49e7cb483d0a1fa47fb75ba2b172";
	$('#errorMessages').empty();
	var weatherInfo = $('#weather-cond');
	var cityText = $('#current-cond');
	var weatherNow = $('#weather-now');
	
	//current
	$.ajax({
		type: 'GET',
		url: 'http://api.openweathermap.org/data/2.5/weather?zip=' + zip + '&APPID=' + key + '&units=' + unit,
		success: function(weatherData){
			console.log('Received data:', weatherData);
			var city = weatherData.name;
				
			cityText.append('<hr/><b>Current Conditions in ' + city + '</b>');

			var temperature = weatherData.main.temp;
			var humidity = weatherData.main.humidity;
			var wind = weatherData.wind.speed;
			var currentCond = weatherData.weather[0].description;
			
			// current weather info
			var row = '<p>';
			row += 'Temperature: ' + temperature + (unit=="metric" ? "&deg;C" : "&deg;F");
			row += '</br>Humidity: ' + humidity + '%';
			row += '</br>Wind: ' + wind + (unit=="metric" ? " km/hour" : " miles/hour");
			row += '</p>';
			
			// current weather type with icon
			var now = '<p>';
			now += "<img src='http://openweathermap.org/img/w/" + (weatherData.weather[0].icon) + ".png' title='" + (weatherData.weather[0].main) + "'>";
			now += 'Current: ' + currentCond;
			now += '</p>';
			
			weatherInfo.append(row);
			weatherNow.append(now);
			
		},
		error: function() {
			$('#errorMessages')
				.append($('<li>')
				.attr({class: 'list-group-item list-group-item-danger'})
				.text('Error calling web service. Please try again later.'));
		}
	});
}

function loadWeather(zip, unit) {
	var key = "773b49e7cb483d0a1fa47fb75ba2b172";
	$('#errorMessages').empty();
	var fiveDayWeather = $('#fiveDayForecast');
	var fiveDayTitle = $('#five-day-text');
	var displayForecast = $('#five-day');
	
	var options = {  
		weekday: "long", month: "short",  
		day: "numeric", hour: "2-digit", minute: "2-digit"  
	};  

	
	//forecast
	$.ajax({
		type: 'GET',
		url: 'http://api.openweathermap.org/data/2.5/forecast?zip=' + zip + '&APPID=' + key + '&units=' + unit,
		success: function(weatherArray){
			console.log('Received data:', weatherArray);
			
			fiveDayTitle.append('<hr/><b>Five Day Forecast</b>');

			$.each(weatherArray.list, function(index, weatherData){
				var tempHigh = weatherData.main.temp_max;
				var tempLow = weatherData.main.temp_min;
				// cant get date formatted correctly -.-
				var dates = new Date(weatherData.dt_txt).toLocaleTimeString("en-us", options);
				
				// always 3 hour intervals, find the 12pm time for each 5 days
				if (index == 4 || index == 12 || index == 20 || index == 28 || index == 36) {
					
				var display = "<div style='width:200px; display:inline-block; margin:10px'><div class='panel panel-default'><div class='panel-heading'><h3 class='panel-title'>";
                    display += dates;
                    display += "</h3></div><div class='panel-body'><table style='width:100%'><tbody><tr><td><img src='http://openweathermap.org/img/w/"
                    display += weatherData.weather[0].icon;
                    display += ".png'></td><td>";
                    display += weatherData.weather[0].main;
                    display += "</td></tr><tr><td>H ";
                    display += tempHigh + (unit=="metric" ? "&deg;C" : "&deg;F");
                    display += "</td><td>L ";
                    display += tempLow + (unit=="metric" ? "&deg;C" : "&deg;F");
                    display += "</td></tr></tbody></table></div></div></div>";	
					
				fiveDayWeather.append(display);
				
				}
			});
		},
		error: function() {
			$('#errorMessages')
				.append($('<li>')
				.attr({class: 'list-group-item list-group-item-danger'})
				.text('Error calling web service. Please try again later.'));
		}
	});
}

function clearWeather() {
	$('#weather-cond').empty();
	$('#current-cond').empty();
	$('#weather-now').empty();
	$('#five-day-weather').empty();
	$('#five-day-text').empty();
	$('#fiveDayForecast').empty();
}

function checkAndDisplayValidationErrors(input) {
	$('#errorMessages').empty();
	
	var errorMessages = [];
	
	input.each(function () {
		if (!this.validity.valid) {
			var errorField = $('label[for=' + this.id + ']').text();
			errorMessages.push(errorField + ' ' + this.validationMessage);
		}
	});
	
	if (errorMessages.length > 0) {
		$.each(errorMessages, function(index,message) {
			$('#errorMessages').append($('<li>').attr({class: 'list-group-item list-group-item-danger'}).text(message));
		});
		return true;
	} else {
		return false;
	}
}
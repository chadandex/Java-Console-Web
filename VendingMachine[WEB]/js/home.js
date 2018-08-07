//Chad Andexler
//The Software Guild


$(document).ready(function () {
	var change = 0.0;
	var productId = 0;
	var totalMoney = $('#totalMoney');
	var endingMoney = $('#change');
	
	// hides if webserver can't be reached
	$('#productMenu').hide();
	
	loadProducts();
	
	//  -- Add Money --
	$('#addDollar').on('click', function(){
		change = addDollar(change);
		var num = '$' + change.toFixed(2).replace(/(\d)(?=(\d\d\d)+(?!\d))/g, "$1,");
		totalMoney.val(num);
	});
	$('#addQuarter').on('click', function(){
		change = addQuarter(change);
		var num = '$' + change.toFixed(2).replace(/(\d)(?=(\d\d\d)+(?!\d))/g, "$1,");
		totalMoney.val(num);
	});
	$('#addDime').on('click', function(){
		change = addDime(change);
		var num = '$' + change.toFixed(2).replace(/(\d)(?=(\d\d\d)+(?!\d))/g, "$1,");
		totalMoney.val(num);
	});
	$('#addNickel').on('click', function(){
		change = addNickel(change);
		var num = '$' + change.toFixed(2).replace(/(\d)(?=(\d\d\d)+(?!\d))/g, "$1,");
		totalMoney.val(num);
	});
	
	// -- Product Menu Clicking -- 
	$('#product1').on('click', function(){
		productId=1;
		$('#item').val(productId);
	});
	$('#product2').on('click', function(){
		productId=2;
		$('#item').val(productId);
	});
	$('#product3').on('click', function(){
		productId=3;
		$('#item').val(productId);
	});
	$('#product4').on('click', function(){
		productId=4;
		$('#item').val(productId);
	});
	$('#product5').on('click', function(){
		productId=5;
		$('#item').val(productId);
	});
	$('#product6').on('click', function(){
		productId=6;
		$('#item').val(productId);
	});
	$('#product7').on('click', function(){
		productId=7;
		$('#item').val(productId);
	});
	$('#product8').on('click', function(){
		productId=8;
		$('#item').val(productId);
	});
	$('#product9').on('click', function(){
		productId=9;
		$('#item').val(productId);
	});
	
	$('#getPurchase').on('click', function(){
		purchaseProduct(change, productId);
	});
	
	$('#getChange').on('click', function(){
		// no input from user check
		if (productId == 0){
			userChange(change);
		}
		
		//refresh program
		if(change >= .01){
			change=0.0;
			productId=0;
			clearVending();
			loadProducts();
		}
	});
});

function loadProducts() {
	
	$.ajax({
		type: 'GET',
		url: 'http://localhost:8080/items',
		success: function(productArray){
			$('#productMenu').show();
			
			console.log('Product data:', productArray);
			
			$.each(productArray, function(index, products){
				var pId = products.id;
				var name = products.name;
				var price = products.price;
				var quantity = products.quantity;
				var num = '$' + price.toFixed(2).replace(/(\d)(?=(\d\d\d)+(?!\d))/g, "$1,");
				
				var	row = '<p align=left>' + pId + '</p>';
					row += '<center>' + name + '</center>';
					row += '<center>' + num + '</center>';
					row += '<br>';
					row += '<center>Quantity Left: ' + quantity + '</center>';
				
				$("#product" + pId + "").append(row);
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

function purchaseProduct(change, productId) {
	$('#errorMessages').empty();
	var machineMsgs = $('#machMessage');
	
	$.ajax({
		type: 'GET',
		url: 'http://localhost:8080/money/' + change + '/item/' + productId,
		success: function(changeInfo){
			console.log('Received data[success]:', changeInfo);
			
			var quarters = changeInfo.quarters;
			var dimes = changeInfo.dimes;
			var nickels = changeInfo.nickels;
			var pennies = changeInfo.pennies;
			var display='';
			
			if(quarters>0){
				display += 'Quarters: ' + quarters + ' ';
			}
			if(dimes>0){
				display += 'Dimes: ' + dimes + ' ';
			}
			if(nickels>0){
				display += 'Nickels: ' + nickels + ' ';
			}
			if(pennies>0){
				display += 'Pennies: ' + pennies + ' ';
			}
			machineMsgs.val('Thank you!!!');
			$('#changeLeft').val(display);
		},
		error: function(xhr) {
			console.log('Received data[error]:', xhr);
			$.each(xhr, function(index, errMsg){
				console.log('index name: ', index);
				if (index == 'responseJSON'){
					console.log('contents:', errMsg.message);
					machineMsgs.val(errMsg.message);
				}
			});
		}
	});
}

function addDollar(change){
	return change + 1.00;
}

function addQuarter(change){
	return change + .25;
}

function addDime(change){
	return change + .10;
}

function addNickel(change){
	return change + .05;
}

// for if user skips 'Make Purchase'
function userChange(change){
	var quarters;
	var dimes;
	var nickels;
	var pennies;
	var display='';
	
	if (change >= .25){
		quarters = change / .25;
		display += 'Quarters: ' + parseInt(quarters) + ' ';
		change = change % .25;
	}
	if (change >= .10){
		dimes = change / .10;
		display += 'Dimes: ' + parseInt(dimes) + ' ';
		change = change % .1;
	}
	if (change >= .05){
		nickels = change / .05;
		display += 'Nickels: ' + parseInt(nickels) + ' ';
		change = change % .05
	}
	if (change >= .01){
		pennies = change / .01;
		display += 'Pennies: ' + parseInt(pennies) + ' ';
	}

	$('#changeLeft').val(display);
}

function clearVending(){
	$('#errorMessages').empty();
	$('#product1').empty();
	$('#product2').empty();
	$('#product3').empty();
	$('#product4').empty();
	$('#product5').empty();
	$('#product6').empty();
	$('#product7').empty();
	$('#product8').empty();
	$('#product9').empty();
	$('#totalMoney').val('');
	$('#machMessage').val('');
	$('#item').val('');
}
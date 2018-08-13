/**
 * 
 */

$(function() {
	var calculatorForm = $('#calculator-input-form');
	var calculatorMessage = $('#calculator-output-message');
	var calculatorError = $('#calculator-error-message');
	var calculatorSpecialOffer = $('#calculator-phone-message');

	$('#btn-calculate').on('click', function(event) {
		$(calculatorMessage).hide();
		$(calculatorError).hide();
		$(calculatorSpecialOffer).hide();

		var ageElement = $(calculatorForm).find('#age');
		var amountElement = $(calculatorForm).find('#amount');
		var age = $(ageElement).val();
		var amount = $(amountElement).val();
		
		if(validateInputs(age, amount)){
			var interest = calculateInterest(age, amount);
			$(calculatorMessage).text("The calculated interest rate is: ");
			$(calculatorMessage).append("<strong>" + interest + "</strong>");
			$(calculatorMessage).show();
		}
	
		return false;
	});

	function validateInputs(age, amount){
		if (!age || age < 18) {
			$(calculatorError).find(".error-message").text("Only adults (aged 18 and older) are eligible to open a term deposit account.");
			$(calculatorError).show();
			return false;
		}
		if(!amount || amount < 100){
			$(calculatorError).find(".error-message").text("The deposit amount must be equal or greater than 100.");
			$(calculatorError).show();
			return false;
		}
		if(amount >= 10000){
			$(calculatorSpecialOffer).show();
			return false;
		}

		return true;
	}

	function calculateInterest(age, amount){
		if(age >= 60){
			return "2.0%"
		}
		if(amount < 1000){
			return "1.0%"
		}
		if(amount < 5000){
			return "1.3%";
		}
		if(amount < 10000){
			return "1.5%";
		}
		return "Oops... Invalid input!";
	}

	$('#btn-special-offer').on('click', function(event) {
		$(calculatorMessage).hide();
		$(calculatorError).hide();

		var specialOfferForm = $("#special-offer-form");
		var phoneElement = $(specialOfferForm).find('#phone');
		var phone = $(phoneElement).val();
		
		if(phone && phone.match(/^((\+|00)32\s?|0)(\d\s?\d{3}|\d{2}\s?\d{2})(\s?\d{2}){2}$/)){
			$(calculatorSpecialOffer).hide();
			$(calculatorMessage).text("Success! We will contact you shortly!");
			$(calculatorMessage).append("<strong></strong>");
			$(calculatorMessage).show();
		}else{
			$(calculatorError).text("The phone number isn't a valid Belgian number.");
			$(calculatorError).show();
		}
	
		return false;
	});
});
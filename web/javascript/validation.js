/* 
 * Validation script using jquery validate plugin
 * 
 * @author Jonathan Anastasiou
 */
$(document).ready(function() {

    $.validator.addMethod("dateTime", function(value, element) {
        var stamp = value.split(" ");
        var validDate = !/Invalid|NaN/.test(new Date(stamp[0]).toString());
        var validTime = /^(([0-1]?[0-9])|([2][0-3])):([0-5]?[0-9])(:([0-5]?[0-9]))?$/i.test(stamp[1]);
        return this.optional(element) || (validDate && validTime);
    }, "Enter a valid date and time hh:mm");

    $("#pejlingForm").validate({
        rules: {
            dguNr: {
                required: true,
                minlength: 4
            },
            pejleTidspunkt: {
                required: true,
                dateTime: true
            },
            vandstand: {
                required: true,
                number: true
            }
        },
        messages: {
            dguNr: {
                required: "You must input a DGU nr.",
                minlength: "DGU nr must be at least 4 characters"
            },
            pejleTidspunkt: {
                required: "You must input a valid date",
                dateTime: "You must input a date in the formmt dd-mm-yyyy"
            },
            vandstand: {
                required: "This field is required",
                number: "This can only be a number"
            }
        },
        errorElement: "span",
        errorPlacement: function(error, element) {
            $(element).siblings(".after").append(error);
        },
        highlight: function(element) {
            $(element).siblings(".after").addClass("validation");
        },
        unhighlight: function(element) {
            $(element).siblings(".after").removeClass("validation");
        },
        errorLabelContainer: ".validation"
    });
});


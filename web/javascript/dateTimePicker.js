/* 
 * Adds a date and time picker to the date input field.
 * 
 * @author Jonathan Anastasiou
 */
var myControl = {
    create: function(tp_inst, obj, unit, val, min, max, step) {
        $("<input class='ui-timepicker-input' value='" + val + "' style='width:50%'>")
                .appendTo(obj)
                .spinner({
            min: min,
            max: max,
            step: step,
            change: function(e, ui) {
                if (e.originalEvent !== undefined)
                {
                    tp_inst._onTimeChange();
                }

                tp_inst._onSelectHandler();
            },
            spin: function(e, ui) {
                tp_inst.control.value(tp_inst, obj, unit, ui.value);
                tp_inst._onTimeChange();
                tp_inst._onSelectHandler();
            }
        });
        return obj;
    },
    options: function(tp_inst, obj, unit, opts, val) {
        if (typeof(opts) === "string" && val !== undefined)
        {
            return obj.find(".ui-timepicker-input").spinner(opts, val);
        }
        return obj.find(".ui-timepicker-input").spinner(opts);
    },
    value: function(tp_inst, obj, unit, val) {
        if (val !== undefined)
        {
            return obj.find(".ui-timepicker-input").spinner("value", val);
        }
        return obj.find(".ui-timepicker-input").spinner("value");
    }
};

$(document).ready(function() {
    $("#dateTimePicker").datetimepicker({
        controlType: myControl,
        dateFormat: "dd/mm/yy",
        changeYear: true,
        changeMonth: true,
        yearRange: "1930:+0"
    });
});
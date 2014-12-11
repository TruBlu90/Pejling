/* 
 * This script is used to get previous pejling info,
 * the refernce punkt kode (and disable the elemnts that are
 * not available) and the indtags nr based on the DGU number that is input
 * 
 * @author Jonathan Anastasiou
 */
$(document).ready(function() {
    $("#dguButton").click(function() {

        $.ajax({
            url: "AjaxPreviousPejling",
            type: "GET",
            data: $("#dgunr"),
            dataType: "json",
            success: function(responseJson) {

                if ($("#prePejDiv").hide())
                {
                    $("#td1").text(responseJson.id);
                    $("#td2").text(responseJson.dgunr);
                    $("#td3").text(responseJson.anvendelse);
                    $("#td4").text(responseJson.formaal);
                    $("#td5").text(responseJson.aar);
                    $("#td6").text(responseJson.dybde);
                    $("#td7").text(responseJson.antalStammer);
                    $("#td8").text(responseJson.tekst);
                    $("#td9").text(responseJson.breddegrad);
                    $("#td10").text(responseJson.laengdegrad);
                    $("#td11").text(responseJson.yeuref89);
                    $("#td12").text(responseJson.xeuref89);

                    $("#prePejDiv").slideDown(2000);
                }


                if ($("#prePejDiv").show())
                {
                    $("#td1").text(responseJson.borId);
                    $("#td2").text(responseJson.dgunr);
                    $("#td3").text(responseJson.anvendelse);
                    $("#td4").text(responseJson.formaal);
                    $("#td5").text(responseJson.aar);
                    $("#td6").text(responseJson.dybde);
                    $("#td7").text(responseJson.antalStammer);
                    $("#td8").text(responseJson.tekst);
                    $("#td9").text(responseJson.breddegrad);
                    $("#td10").text(responseJson.laengdegrad);
                    $("#td11").text(responseJson.yeuref89);
                    $("#td12").text(responseJson.xeuref89);
                }


            }

        });

        $.ajax({
            url: "AjaxReferenceKode",
            type: "GET",
            data: $("#dgunr"),
            dataType: "json",
            success: function(json) {

                if (json.maalepunkt === "N/A")
                {
                    $("#maalepunkt").hide(500);
                    $("#maal").hide(500);

                }

                if (json.terraen === "N/A")
                {
                    $("#terraen").hide(500);
                    $("#ter").hide(500);
                }

                if (json.fikspunkt === "N/A")
                {
                    $("#fikspunkt").hide(500);
                    $("#fiks").hide(500);
                }

                if (json.maalepunkt === "M")
                {
                    $("#maalepunkt").show(500);
                    $("#maal").show(500);
                }

                if (json.terraen === "T")
                {
                    $("#terraen").show(500);
                    $("#ter").show(500);
                }

                if (json.fikspunkt === "K")
                {
                    $("#fikspunkt").show(500);
                    $("#fiks").show(500);
                }
            }
        });

        $.ajax({
            url: "AjaxIndtagsNr",
            type: "GET",
            data: $("#dgunr"),
            dataType: "json",
            success: function(resjson) {
                console.log(resjson);
                $("#indtagsnr").get(0).options.length = 0;
                $("#indtagsnr").get(0).options[0] = new Option("Vælg indtags nr.");
                $.each(resjson, function(index, item) {
                    $("#indtagsnr").get(0).options[$("#indtagsnr").get(0).options.length] = new Option(item.indtagsnr);

                });
            }
        });
    });

    $("#hideTable").click(function() {
        $("#prePejDiv").slideUp(2000);
    });


});



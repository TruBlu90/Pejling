<%-- 
    Document   : savePejling
    Created on : 19-08-2013, 10:40:44
    Author     : Jonathan Anastasiou & Richard Haley III
--%>

<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link href='http://fonts.googleapis.com/css?family=Lato:400,700' rel='stylesheet' type='text/css'>
        <link rel = "stylesheet" type = "text/css" href = "css/geus.css">
        <link rel="stylesheet" type="text/css" href="css/validation.css">
        <link rel="stylesheet" type="text/css" href="css/jquery-ui-1.10.3.custom.css">
        <link rel="stylesheet" type="text/css" href="css/jquery-ui-timepicker-addon.css">
        <script type="text/javascript" src="javascript/jquery-1.10.2.js"></script>
        <script type="text/javascript" src="javascript/jquery-ui-1.10.3.custom.js"></script>
        <script type="text/javascript" src="javascript/jquery-ui-timepicker-addon.js"></script>
        <script type="text/javascript" src="javascript/jquery.validate.js"></script>
        <script type="text/javascript" src="javascript/ajaxControls.js"></script>
        <script type="text/javascript" src="javascript/dateTimePicker.js"></script>
        <script type="text/javascript" src="javascript/validation.js"></script>
        <script type="text/javascript" src="javascript/showGraph.js"></script>
        <style type="text/css">

            body{
                font-family: 'Lato', sans-serif;
            }

            #header{               
                background-image: url(images/fading_background_s.png);
                background-repeat: repeat-x;
                border-bottom: 2px solid #E6E6E6;
                width: 100%;
                overflow: hidden;
            }

            #wrapper{
                width: 100%;
            }

            #formContainer{
                width: 33%;
                float: left;
            }

            .imgPosition{
                padding-top: 65px;
            }

            #prePejDiv{
                float: left;
                margin-left: 50px;
                margin-bottom: 5px;
            }

            #prePejTable{

                margin-top: 10px;
            }

            #prePejTable td, th{
                padding: 5px;
            }

            #chartOptions{
                padding-top: 400px;   
                margin-bottom: 50px;
            }
            
            #chart{
                margin-right: 500px;
            }
        </style>

        <title>Indtast ny pejling</title>
    </head>
    <body>
        <div id="header">
            <img src="http://www.geus.dk/repository/icons/logo-30.gif" align="left" height="100px">
            <img src="http://www.geus.dk/repository/icons/dggu-dk.gif" align="left" class="imgPosition">
        </div>
        <div id="wrapper">
            <h3>Intast ny pejling</h3>
            <div id="formContainer">
                <form id="pejlingForm" action="FrontControllerServlet" method="get">
                    <fieldset>
                        <p>
                            <label for="dgunr">DGU nr:</label>                 
                            <input type="text" name="dguNr" id="dgunr" ><span class="after"></span>

                        </p>
                        <p>
                            <label for="indtagsnr">Indtag:</label>
                            <select name="indtagsNr" id="indtagsnr">                              
                            </select>
                        </p>
                        <input type="button" id="dguButton" value="Søg">
                    </fieldset>
                    <br>
                    <fieldset>
                        <p>
                            <label for="dateTimePicker">Tidspunkt:</label>
                            <input type="text" name="pejleTidspunkt" id="dateTimePicker" ><span class="after"></span>
                        </p>

                        <p>
                            <label for="vandstand">Vanstand:</label>
                            <input type="text" name="vandstand" id="vandstand" size=4><span>m.</span><span class="after"></span>
                        </p>
                        <p>
                            <label for="ref">Reference Punkt:</label>
                            <span id="ref">
                                <input type="radio" id="maalepunkt" name="referencePunkt" value = "M"><label id="maal" for="maalepunkt">Under målepunkt</label>
                                <input type="radio" id="terraen" name="referencePunkt" value="T"><label id="ter" for="terraen">Under terræn</label>
                                <input type="radio" id="fikspunkt" name="referencePunkt" value="K"><label id="fiks" for="fikspunkt">Fikspunkt</label>
                            </span>
                        </p>

                        <p>

                            <label for="pejleProjekt">Projekt:</label>
                            <input type="radio" name="pejleProjekt" id="pejleProjekt" value="Andet">Andet
                            <input type="radio" name="pejleProjekt" id="pejleProjekt" value="GRUMO">GRUMO

                        </p>
                        <p>
                            <label for="pejleSituation">Situation:</label>

                            <input type="radio" name="pejleSituation" id="pejleSituation" value="Rovand">Rovand
                            <input type="radio" name="pejleSituation" id="pejleSituation" value="I drift">I drift


                        </p>
                        <p>
                            <label for="pejleEkstremer">Ekstremer:</label>

                            <input type="radio" name="pejleEkstremer" id="pejleEkstremer" value="Ingen">Ingen
                            <input type="radio" name="pejleEkstremer" id="pejleEkstremer" value="Overløb">Overløb
                            <input type="radio" name="pejleEkstremer" id="pejleEkstremer" value="Tør">Tør 
                        </p>
                        <p>   
                            <input type="submit" id="savePejlingButton" name="savePejlingButton" value="Gem">
                            <input type="reset" name="nuls" value="Nulstil">
                            <input type="button" id="showChart" value="Vis Graf">
                        </p>
                    </fieldset>
                </form>
            </div>
            <br/>
            <div id="prePejDiv" style="display:none">
                <table id="prePejTable" border = "1" cellspacing="0">
                    <tr>
                        <td>Bor Id</td>
                        <td id="td1"></td>
                    </tr>
                    <tr>
                        <td>DGU nr.</td>
                        <td id="td2"></td>
                    </tr>
                    <tr>
                        <td>Anvendelse</td>
                        <td id="td3"></td>
                    </tr>
                    <tr>
                        <td>Formål</td>
                        <td id="td4"></td>
                    </tr>
                    <tr>
                        <td>År</td>
                        <td id="td5"></td>
                    </tr>
                    <tr>
                        <td>Dybde</td>
                        <td id="td6"></td>
                    </tr>
                    <tr>
                        <td>Antal Stammer</td>
                        <td id="td7"> </td>
                    </tr>
                    <tr>
                        <td>Tekst</td>
                        <td id="td8"></td>
                    </tr>
                    <tr>
                        <td>Breddegrad</td>
                        <td id="td9"></td>
                    </tr>
                    <tr>
                        <td>Længdegrad</td>
                        <td id="td10"></td>
                    </tr>
                    <tr>
                        <td>YEUREF89</td>
                        <td id="td11"></td>
                    </tr>
                    <tr>
                        <td>XEUREF89</td>
                        <td id="td12"></td>
                    </tr>
                </table>
                <input type="button" id="hideTable" value="Fjern"/>
            </div>
            <div id="chartOptions"> 
            <div id="chart">



            </div>
                <br/>       
                <label for="nrOfYears">Vælg antal år:</label>
                <select id="nrOfYears">
                    <option name="alle" value="alle">Alle</option>
                    <option name="sidste1" value="sidste1">Sidste år</option>
                    <option name="sidste10" value="sidste10">Sidste 10 år</option>
                    <option name="sidste20" value="sidste20">Sidste 20 år</option>
                    <option name="sidste30" value="sidste30">Sidste 30 år</option>
                </select>
                <input type="button" id="updateChart" value="Opdatere"/>
                <input type="button" id="removeChart" value="Fjern Graf"/>
            </div>
        </div>
    </body>
</html>
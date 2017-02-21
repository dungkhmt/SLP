<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!-- DataTables JavaScript -->
<script src="<c:url value="/assets/libs/RGraph-SVG/js/RGraph.svg.bar.js"/>"></script>
<script src="<c:url value="/assets/libs/RGraph-SVG/js/RGraph.svg.common.core.js"/>"></script>

<div id="page-wrapper">
	<div class="row">
		<div class="col-lg-12">
			<h1 class="page-header">Thống kê doanh số</h1>
		</div>
		<!-- Modal -->
	  <div class="modal fade" id="changeDate" role="dialog">
	    <div class="modal-dialog">
	    
	      <!-- Modal content-->
	      <div style="top:180px" class="modal-content">
	        <div class="modal-header">
	          <button type="button" class="close" data-dismiss="modal">&times;</button>
	          <h4 class="modal-title">Thay đổi thời gian thống kê</h4>
	        </div>
	        <div class="modal-body">
	          <form>
			    <div class="form-group">
			      	<label>From </label>
        			<input type="date" id="fromDate"  class="date"/>
        			<label>To </label>
        			<input type="date" id="toDate" class="date">
        			<label>Type </label>
        			<select id="type">
					  <option value="day">Ngày</option>
					  <option value="month">Tháng</option>
					  <option value="year">Năm</option>
					</select>
			    </div>
		    	</form>
	        </div>
	        <div class="modal-footer">
	        	<button type="button" class="btn btn-success" onclick="update()">Choose</button>
	         	<button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
	        </div>
	      </div>
	      
	    </div>
	  </div>
		<div class="col-lg-12">
		<button type="button" class="btn btn-primary" data-toggle="modal" data-target="#changeDate">Thêm</button>
		</div>
		<!-- /.col-lg-12 -->
	</div>
	
	<div class="row" id="chart">
		
	</div>
	<!-- /.row -->
</div>
<!-- /#page-wrapper -->

<script>
    // This is the source data
    var dataStatics = {
    	source: [],
    	// These are for once the data has been extracted and split up from
        // the source
        data:   [],
        labels: []
    };
    
    function update() {
    	var from = document.getElementById("fromDate").value;
    	var to = document.getElementById("toDate").value;
    	var type = document.getElementById("type").value;
    	if(from !== null & to !== null) {
    		
    		
    		$.get(window.location.href + "/datadeliveried?type="+type+"&to="+to+"&from="+from, function(data) {
    			if(data.length>0) {
    				$( "#chart-container" ).remove();
        			// Reverse the data so that the latest figure is on the right
            	    //data.total = RGraph.SVG.arrayReverse(data.total);
            	    dataStatics = {
    			    	source: [],
    			    	// These are for once the data has been extracted and split up from
    			        // the source
    			        data:   [],
    			        labels: []
    			    };
            	    // Loop through the source data extracting the required parts
            	    for (var i=0; i<data.length; ++i) {
            	    	data[i].total = data[i].total/1000;
            	    	dataStatics.source.push(data[i])
            	        dataStatics.labels.push(data[i].date);
            	    	dataStatics.data.push(data[i].total);
            	    }
            	    $("#chart").append('<div id="chart-container" style="width:'+(dataStatics.data.length*120 + 210)+'; height:500px"></div>');
					if(dataStatics.data.length>1) {
						new RGraph.SVG.Bar({
	            	        id: 'chart-container',
	            	        data: dataStatics['data'],
	            	        options: {
	            	            xaxisLabels: dataStatics['labels'],
	            	            colors: ['Gradient(#40ff00:white)'],
	            	            strokestyle: 'black',
	            	            textFont: 'Monospace',
	            	            textSize: 10,
	            	            yaxisMax: 	(Math.floor(Math.max.apply(Math,dataStatics.data)/100) +1)*100,
	            	            hmargin: 8,
	            	            gutterLeft: 200,
	            	            gutterTop: 50,
	            	            backgroundGridLinewidth: 2,
	            	            backgroundGridColor: '#eee',
	            	            backgroundGridVlines: false,
	            	            backgroundGridBorder: false,
	            	            yaxis: true,
	            	            yaxisUnitsPost: ' k',
	            	            xaxis: false,
	            	            yaxisDecimals: 1,
	            	            labelsAbove: true,
	            	            labelsAboveDecimals: 1,
	            	            title: '',
	            	            titleSize: 10,
	            	            titleHalign: 'left',
	            	            titleX: 50,
	            	            titleBold: true,
	            	            titleItalic: true,
	            	            linewidth: 0.75
	            	        }
	            	    }).wave();
					} else {
						new RGraph.SVG.Bar({
	            	        id: 'chart-container',
	            	        data: dataStatics['data'],
	            	        options: {
	            	            xaxisLabels: dataStatics['labels'],
	            	            colors: ['Gradient(#40ff00:white)'],
	            	            strokestyle: 'black',
	            	            textFont: 'Monospace',
	            	            textSize: 10,
	            	            yaxisMax: 	(Math.floor(Math.max.apply(Math,dataStatics.data)/100) +1)*100,
	            	            hmargin: 8,
	            	            gutterLeft: 200,
	            	            gutterTop: 50,
	            	            backgroundGridLinewidth: 2,
	            	            backgroundGridColor: '#eee',
	            	            backgroundGridVlines: false,
	            	            backgroundGridBorder: false,
	            	            yaxis: true,
	            	            yaxisUnitsPost: ' k',
	            	            xaxis: false,
	            	            yaxisDecimals: 1,
	            	            labelsAbove: true,
	            	            labelsAboveDecimals: 1,
	            	            title: '',
	            	            titleSize: 10,
	            	            titleHalign: 'left',
	            	            titleX: 50,
	            	            titleBold: true,
	            	            titleItalic: true,
	            	            linewidth: 0.75
	            	        }
	            	    }).draw();
					}
            	   
            	    $('#changeDate').modal('hide');
    			}
        	});
    	}
    }
    
    
    
</script>
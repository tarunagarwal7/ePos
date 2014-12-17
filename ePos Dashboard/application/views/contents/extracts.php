<div id="page-content-wrapper">
<!-- Page Content -->
  <div class="container-fluid" style="font-size:90%;">
                                                   
    <hr style="margin-bottom:10px" />
    <div>  
          <form id="selRest" method="POST" role="form">
          <ul class="list-inline">
            <li>
              <div class="form-group" style="margin-bottom:0px">
                <div class="input-group">
                  <div class="input-group-addon"><span class="glyphicon glyphicon-calendar"></span></div>
                  <input id="startdate" name="startdate" type="text" value="<?=$startdate?>" class="form-control datepicker" style="display:inline;padding-left:10px;padding-right:-20px" title="Start Date">
                </div>                                                                                                                                                              
              </div>
            </li>
            <li>                                                                                                                                                                            
               <div class="form-group" style="margin-bottom:0px">
                  <div class="input-group">       
                    <div class="input-group-addon"><span class="glyphicon glyphicon-calendar"></span></div>
                    <input id="enddate" name="enddate" type="text" value="<?=$enddate?>" class="form-control datepicker" style="display:inline;padding-left:10px;padding-right:-20px" title="End Date">
                  </div>
               </div>
            </li>     
            <li>     
               <div class="form-group" style="margin-bottom:0px">
                  <div class="input-group">
                    <div class="input-group-addon"><span class="glyphicon glyphicon-cutlery"></span></div>
                    <select id = "myRestaurant" name="rest_id" title="Restaurant Name" class="form-control" style="display:inline">
                      <option value = "0">ALL Restaurants</option>
                      <?php foreach($restaurants as $row){ ?>
                      <option value = "<?=$row->REST_ID?>" <?= ($row->REST_ID==$rest_id)?'selected':''?> ><?=$row->NAME?></option>
                      <?php } ?>
                    </select>   
                  </div>
               </div>
            </li>
            <li>      
               <div class="form-group" style="margin-bottom:0px">
                  <div class="input-group">
                    <button type="submit" class="btn btn-success" style="display:inline">Filter</button>   
                  </div>
               </div>
            </li>
          </ul>
          </form>
		      <hr style="margin-top:-5px" />
	  </div>
	   <div class="panel panel-default">
		    <div class="panel-heading">
          <b><?=$data_name?> Data</b>  
        </div>
	      <div class="panel-body">
	         <a class="btn btn-lg btn-success" href="?p=xls&rest_id=<?=$rest_id?>&startdate=<?=$startdate?>&enddate=<?=$enddate?>">
              <i class="fa fa-file-excel-o"></i> download xls
           </a>
	         <a class="btn btn-lg btn-danger" href="?p=pdf&rest_id=<?=$rest_id?>&startdate=<?=$startdate?>&enddate=<?=$enddate?>">
              <i class="fa fa-file-pdf-o"></i> download pdf
           </a>
			  </div>
			</div>
		</div>
    <!-- FOOTER -->
    <hr class="featurette-divider" />
    
    <footer>
      <p class="pull-right">
        <a href="#">Back to top <span class="glyphicon glyphicon-circle-arrow-up"></span></a>
      </p>
	</footer>
  
  </div><!-- /.container-fluid -->
</div><!-- /#page-content-wrapper -->


<script type="text/javascript">      
     //datepickers    
     $("#startdate").datepicker({format: 'dd M yyyy'});
     $("#enddate").datepicker({format: 'dd M yyyy'});
</script>

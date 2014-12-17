<div id="page-content-wrapper">
<!-- Page Content -->
  <div class="container-fluid" style="font-size:90%;">
  
    <div class="btn-group" role="group" aria-label="..." style="margin-top:10px;">
      <a role="button" class="btn btn-default" href="?p=">Sales</a>
      <a role="button" class="btn btn-primary" href="?p=inventory">Inventory</a>         
    </div>                                                                       
    <hr style="margin-bottom:10px" />
    <?php //$this->load->view('contents/setting',$data);

    ?>
    <div>  
          <form id="selRest" method="POST" role="form">
          <ul class="list-inline">
            <li>     
               <div class="form-group" style="margin-bottom:0px">
                  <div class="input-group">
                    <div class="input-group-addon"><span class="glyphicon glyphicon-cutlery"></span></div>
                    <select id = "myRestaurant" name="rest_id" title="Restaurant Name" class="form-control" style="display:inline" disabled>
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
          <b>Inventory Report</b>  
        </div>
        <?php ?>
	      <div class="panel-body">  
	        <table data-toggle="table" data-url=""  data-show-refresh="false" data-show-toggle="false" data-show-columns="true" data-search="true" data-select-item-name="toolbar1" data-pagination="true" data-sort-name="name" data-sort-order="desc">
					  <thead>
						  <tr>
						    <th data-field="state" data-checkbox="true" >Inv ID</th>
						    <th data-field="name" data-sortable="true">Name</th>
						    <th data-field="qty"  data-sortable="true">Quantity</th>
						    <th data-field="stat" data-sortable="true">Status</th>
						  </tr>
						</thead>
						<tbody>           
						  <?php $i = 0;  foreach ($inventory as $row){ ?>
						  <tr class="<?=$this->report->inv_status_class($row->STATUS)?>">
						    <td data-field="state" data-checkbox="true" ><?=$i?></td>
						    <td data-field="name" data-sortable="true"><?=$row->NAME?></td>
						    <td data-field="qty"  data-sortable="true"><?=$row->QUANTITY?></td>
						    <td data-field="stat" data-sortable="true"><?=$row->STATUS?></td>
						  </tr>
						  <?php $i++; } ?>
						</tbody>
					</table>
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

<!-- Modal -->
<div class="modal fade" id="bookModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
  <div class="modal-dialog">
    <div class="modal-content">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">&times;</span><span class="sr-only">Close</span></button>
        <h4 class="modal-title" id="myModalLabel">Book a Facility</h4>
      </div><!-- /.modal-header -->
      <div class="modal-body">
        <form role="form">
          <div class="form-group">
            <label for="bookId">Booking ID -</label> <span class="text-disabled">A108</span>
          </div>

          <div class="form-group">
            <label for="inputCaption">Caption</label>
            <input type="text" class="form-control" id="inputCaption" placeholder="">
          </div>
          <div class="form-group">
            <label for="inputDate">Date</label>
            <input type="text" class="form-control datepicker" id="inputDate" placeholder="">
          </div>
          <div class="form-group">
            <label for="inputDate">Time</label><br />
            <div class="col-sm-6">
            <select name="timeFrom" class="btn btn-default">
              <option>08:00</option>
              <option>08:30</option>
              <option>09:00</option>
              <option>09:30</option>
            </select> 
            -
            <select name="timeTo" class="btn btn-default">
              <option>08:00</option>
              <option>08:30</option>
              <option>09:00</option>
              <option>09:30</option>
            </select>
            </div>
          </div><br />
          <div class="form-group text-right">
            <button type="submit" class="btn btn-success">Submit</button>
            <button type="button" class="btn btn-warning" data-dismiss="modal">Close</button>
          </div>
        </form>
      </div><!-- /.modal-body -->
    </div><!-- /.modal-content -->
  </div><!-- /.modal-dialog -->
</div><!-- /.modal fade -->

<script>      
     //datepickers
     //$("#startdate").datepicker({format: 'dd M yyyy'});
     //$("#enddate").datepicker({format: 'dd M yyyy'});

</script>

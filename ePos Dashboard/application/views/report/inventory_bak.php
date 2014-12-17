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
                    <div class="input-group-addon"><span class="glyphicon glyphicon-file"></span></div>
                    <select id = "repch" name="report_name" title="Choose Report" class="form-control" style="display:inline">
                      <option value = "0">Choose Report</option>
                      <option value = "Sales" <?=($report_name=='Sales')?'selected':''?>>Sales</option>
                      <option value = "Void Items" <?=($report_name=='Void Items')?'selected':''?>>Void Items</option>
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
          <b><?=$report_name?> Report</b>  
        </div>
	      <div class="panel-body">
	       <?php if($report_name!="Sales"){?>   
	        <table data-toggle="table" data-url=""  data-show-refresh="false" data-show-toggle="false" data-show-columns="true" data-search="true" data-select-item-name="toolbar1" data-pagination="true" data-sort-name="name" data-sort-order="desc">
					  <thead>
						  <tr>
						    <th data-field="state" data-checkbox="true" >Void ID</th>
						    <th data-field="name" data-sortable="true">Menu Name</th>
						    <th data-field="rson"  data-sortable="true">Void Reason</th>
						    <th data-field="onum" data-sortable="true">Order Number</th>
						    <th data-field="strd" data-sortable="true">Started</th>
						    <th data-field="endd"  data-sortable="true">Ended</th>
						  </tr>
						</thead>
						<tbody>           
						  <?php $i = 0;  foreach ($void_items as $row){ ?>
						  <tr>
						    <td data-field="state" data-checkbox="true" ><?=$i?></td>
						    <td data-field="name" data-sortable="true"><?=$row->NAME?></td>
						    <td data-field="rson"  data-sortable="true"><?=$row->VOID_REASON?></td>
						    <td data-field="onum" data-sortable="true"><?=$row->ORDER_NUMBER?></td>
						    <td data-field="strd" data-sortable="true"><?=$row->STARTED?></td>
						    <td data-field="endd"  data-sortable="true"><?=$row->ENDED?></td>
						  </tr>
						  <?php $i++; } ?>
						</tbody>
					</table>
				<?php } else {?>  
	         <table data-toggle="table" data-url=""  data-show-refresh="false" data-show-toggle="false" data-show-columns="true" data-search="true" data-select-item-name="toolbar1" data-pagination="true" data-sort-name="name" data-sort-order="desc">
					   <thead>
						  <tr>
						    <th data-field="state" data-checkbox="true">Order ID</th>
						    <th data-field="name" data-sortable="true">Order Number</th>
						    <th data-field="rest"  data-sortable="true">Table Number</th>
						    <th data-field="conn" data-sortable="true">Customer</th>
						    <th data-field="ip" data-sortable="true">Started</th>
						    <th data-field="port"  data-sortable="true">Ended</th>
						    <th data-field="crby" data-sortable="true">Num Of Guest</th>
						    <th data-field="crdt" data-sortable="true">Total</th>
						    <th data-field="crdt" data-sortable="true">Tip</th>
						    <th data-field="crdt" data-sortable="true">Discount</th>
						    <th data-field="upby"  data-sortable="true">Paid Amount</th>
						  </tr>
						</thead>
						<tbody>   
						  <?php $i = 0;  foreach ($sales_report as $row){ ?>
						  <tr>
						    <td data-field="state" data-checkbox="true" ><?=$row->ID?></td>
						    <td data-field="name" data-sortable="true"><?=$row->ORDER_NUMBER?></td>
						    <td data-field="rest"  data-sortable="true"><?=$row->TABLE_NUMBER?></td>
						    <td data-field="conn" data-sortable="true"><?=$row->CUSTOMER_NAME?></td>
						    <td data-field="ip" data-sortable="true"><?=$row->STARTED?></td>
						    <td data-field="port"  data-sortable="true"><?=$row->ENDED?></td>
						    <td data-field="crby" data-sortable="true"><?=$row->NO_OF_GUEST?></td>
						    <td data-field="crdt" data-sortable="true"><?=$row->TOTAL?></td>
						    <td data-field="crdt" data-sortable="true"><?=$row->TIP?></td>
						    <td data-field="crdt" data-sortable="true"><?=$row->DISCOUNT?></td>
						    <td data-field="upby"  data-sortable="true"><?=$row->PAID_AMOUNT?></td>
						  </tr>
						  <?php $i++; } ?>
						</tbody>
					</table> 
				<?php } ?>
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
     $("#startdate").datepicker({format: 'dd M yyyy'});
     $("#enddate").datepicker({format: 'dd M yyyy'});

</script>

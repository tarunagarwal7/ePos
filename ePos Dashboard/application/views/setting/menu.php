<div id="page-content-wrapper">
<!-- Page Content -->
  <div class="container-fluid" style="font-size:90%;">
  
    <div class="btn-group" role="group" aria-label="..." style="margin-top:10px;">
      <a role="button" class="btn btn-default" href="<?=base_url()?>setting/restaurant">Restaurant</a>                
      <a role="button" class="btn btn-default" href="<?=base_url()?>setting/category">Category</a>               
      <a role="button" class="btn btn-primary" href="<?=base_url()?>setting/menu">Menu</a>             
      <a role="button" class="btn btn-default" href="<?=base_url()?>setting/tableorder">Table Order</a>        
      <a role="button" class="btn btn-default" href="<?=base_url()?>setting/users">Users</a>               
      <a role="button" class="btn btn-default" href="<?=base_url()?>setting/printer">Printer</a>       
      <a role="button" class="btn btn-default" href="<?=base_url()?>setting/devices">Devices</a>           
    </div>       
                                                                          
    <hr style="margin-bottom:10px" />
    
    <div class="row" style="padding-left: 15px">  
      <?php
        $attributes = array('class' => 'form-inline', 'id' => 'filter', 'role' => 'form');
        echo form_open('setting/menu',$attributes)
      ?>
        <div class="form-group" style="margin-bottom:0px">
          <div class="input-group">
            <div class="input-group-addon"><span class="glyphicon glyphicon-cutlery"></span></div>
            <select id = "myRestaurant" name="rest_id" title="Restaurant Name" class="form-control" style="display:inline">
              <option value = "0">Select Restaurant</option>
              <?php foreach($restaurants as $row){ ?>
              <option value = "<?=$row->REST_ID?>" <?= ($row->REST_ID==$rest_id)?'selected':''?> ><?=$row->NAME?></option>
              <?php } ?>
            </select>   
          </div>
        </div>
        <div class="form-group" style="margin-bottom:0px">
          <div class="input-group">
            <button type="submit" class="btn btn-success" style="display:inline">Filter</button>   
          </div>
        </div>
      <?=form_close()?>     
    </div>              
                                                     
    <hr style="margin-bottom:10px;margin-top:10px" />
    
    <div class="row">
			<div class="col-lg-12">
				<div class="panel panel-default">
					<div class="panel-heading"><b>Add/Edit Menu</b></div>
					<div class="panel-body">                 					                    
			      <button type="button" class="btn btn-success" data-toggle="modal" data-target="#bookModal">
              <span class="glyphicon glyphicon-plus"></span> Add New Menu
            </button>             
            <button type="button" class="btn btn-danger">
              <span class="glyphicon glyphicon-remove"></span> Delete Selected Menu(s)
            </button>        
            <div style="margin-bottom:15px"></div> 
            <div class="table-responsive">
						  <table id="setting" class="table table-striped dt-right compact">
						    <thead>
						    <tr class="tablehead text3D">
						        <th class="no-sort"><input type="checkbox" id="checkall" value="Check All"></th>
						        <th>Menu Name</th>
						        <th>Price</th>
						        <th>Category</th>
						        <th>Printer</th>
						        <th>Tax</th>
						        <th data-field="crby" data-sortable="false">Created By</th>
						        <th data-field="crdt" data-sortable="false">Created Date</th>
						        <th data-field="upby"  data-sortable="false">Updated By</th>
						        <th data-field="updt" data-sortable="false">Updated Date</th>
						    </tr>
						    </thead>  
						    <tbody>                    
						    <?php $i = 0;  foreach ($menus as $row){ ?>
                <tr data-index="<?=$i?>" class="datarow" id="<?=$row->ID.'_'.$row->NAME?>">
                  <td class="">
                    <input type="checkbox" class="case" tabindex="-1">
                  </td>
                  <td style="">
                    <a id="NAME-<?=$row->ID?>" class="edit" tabindex><?=$row->NAME?></a>
                  </td>
                  <td style="">
                    <a id="PRICE-<?=$row->ID?>" class="edit" tabindex><?=$row->PRICE?></a>
                  </td>
                  <td style="">
                    <a id="CATEGORY_ID-<?=$row->ID?>" class="edit" tabindex><?=$row->CAT_NAME?></a>
                  </td>
                  <td style="">
                    <a id="PRINTER-<?=$row->ID?>" class="edit" tabindex><?=$row->PRINT_NAME?></a>
                 </td>
                  <td style="">
                    <a id="TAX-<?=$row->ID?>" class="edit" tabindex><?=$row->TAX?></a>
                  </td>
                  <td style=""><span id="crby<?=$row->ID?>"><?=$this->setting->get_username($row->CREATED_BY)->USERNAME?></span></td>
                  <td style=""><span id="crdt<?=$row->ID?>"><?=$row->CREATED_DATE?></span></td>
                  <td style=""><span id="upby<?=$row->ID?>"><?=$this->setting->get_username($row->LAST_UPDATED_BY)->USERNAME?></span></td>
                  <td style=""><span id="updt<?=$row->ID?>"><?=$row->LAST_UPDATED_DATE?></span></td>
                </tr>
                <?php $i++; } ?>
						    </tbody>
						  </table>
             </div> 
					</div><!--/.panel-body-->
				</div><!--/.panel-->
			</div><!--/.col-lg-12-->
		</div><!--/.row-->
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
        <h4 class="modal-title" id="myModalLabel">Add New Menu</h4>
      </div><!-- /.modal-header -->
      <div class="modal-body">
      <?php
        $attributes = array('class' => 'form-inline', 'id' => 'newcat', 'role' => 'form');
        echo form_open('setting/menu',$attributes)
      ?>                    		
        <div class="form-group" style="margin-bottom:10px"> 
          <div class="input-group">       
            <label for="inputCaption">Menu Name</label>
            <input type="text" class="form-control" id="inputCaption" placeholder="" name="menu_name" required>
          </div>
        </div><br />
        <div class="form-group" style="margin-bottom:10px"> 
          <div class="input-group col-xs-5">       
            <label for="inputCaption">Price</label>
            <input type="text" class="form-control" id="inputCaption" placeholder="please fill in a number format" pattern="\d*" name="menu_price" required>
          </div>
        </div><br />     
        <div class="form-group" style="margin-bottom:10px">
          <div class="input-group">       
            <label for="inputDate">Category</label><br />  
            <select name="menu_category" class="form-control">
            <?php foreach($categories as $rowc){ ?>
              <option value="<?=$rowc->ID?>"><?=$rowc->NAME?></option>
            <?php } ?>
            </select>
          </div>
        </div><br />     
        <div class="form-group" style="margin-bottom:10px">
          <div class="input-group">       
            <label for="inputDate">Printer</label><br />  
            <select name="menu_printer" class="form-control">
            <?php foreach($printer as $rowp){ ?>
              <option value="<?=$rowp->ID?>"><?=$rowp->NAME?></option>
            <?php } ?>
            </select>
          </div>
        </div><br />  
        <div class="form-group" style="margin-bottom:10px"> 
          <div class="input-group col-xs-3">       
            <label for="inputCaption">Tax</label>
            <input type="text" class="form-control" id="inputCaption" placeholder="please fill in a number format" pattern="\d*" name="menu_tax" required>
             %
          </div>
        </div><br />
        <div class="form-group" style="margin-bottom:10px"> 
          <div class="input-group">       
            <label for="inputDate">Restaurant</label><br /> 
            <select name="rest_id" class="form-control">
            <?php foreach($restaurants as $rows){ ?>
              <option value = "<?=$rows->REST_ID?>" <?= ($rows->REST_ID==$rest_id)?'selected':''?> ><?=$rows->NAME?></option>
            <?php } ?>
            </select>
          </div>
        </div><br />  
        <div class="form-group text-right" style="margin-bottom:10px">
          <div class="input-group">       
            <button type="submit" class="btn btn-success">Submit</button>
            <button type="button" class="btn btn-warning" data-dismiss="modal">Close</button>
          </div>
        </div><br /> 
        <?=form_close()?>
      </div><!-- /.modal-body -->
    </div><!-- /.modal-content -->
  </div><!-- /.modal-dialog -->
</div><!-- /.modal fade -->
<?php  
  //editable script
  $i = 0;
  //$n = count($dtopcats);
  $edit_script = "<script>"; 
  $edit_script .= "$(document).ready(function(){";
  $edit_script .= "  $.fn.editable.defaults.mode = 'inline';";
  foreach ($menus as $row){
  $edit_script .= "  $('#NAME-".$row->ID."').editable({
                        url: '/process/menu?p=update',
                        pk: ".$row->ID.", 
                        validate: function(v) {
                          if (!v) return 'don\'t leave it blank!';
                        },
                        success: function(result){  
                          var data = result.split(',');
                          $('#upby".$row->ID."').html(data[0]);
                          $('#updt".$row->ID."').html(data[1]); 
                      } 
                    });";
  $edit_script .= "  $('#PRICE-".$row->ID."').editable({
                        url: '/process/menu?p=update',
                        pk: ".$row->ID.", 
                        validate: function(v) {
                          if (!v) return 'don\'t leave it blank!'; 
                          if (isNaN(v)) return 'please fill in a number format!';
                        },
                        success: function(result){  
                          var data = result.split(',');
                          $('#upby".$row->ID."').html(data[0]);
                          $('#updt".$row->ID."').html(data[1]); 
                      } 
                    });";
  $edit_script .= "  $('#CATEGORY_ID-".$row->ID."').editable({
                        type: 'select',  
                        value: '".$row->CATEGORY_ID."', 
                        source: [ ";
  $k = 1;
  $q = count($categories);
  foreach($categories as $rowc){
    $edit_script .= "  {value: '".$rowc->ID."', text: '".$rowc->NAME."'}";
    $edit_script .= ($k<$q)?", ":"";
    $k++;
  }
  $edit_script .= "    ],
                        url: '/process/menu?p=update',
                        pk: ".$row->ID.",
                        success: function(result){  
                          var data = result.split(',');
                          $('#upby".$row->ID."').html(data[0]);
                          $('#updt".$row->ID."').html(data[1]); 
                        }  
                      });";
  $edit_script .= "  $('#PRINTER-".$row->ID."').editable({
                        type: 'select',  
                        value: '".$row->PRINTER."', 
                        source: [ ";
  $i = 1;
  $n = count($printer);
  foreach($printer as $rowp){
    $edit_script .= "  {value: '".$rowp->ID."', text: '".$rowp->NAME."'}";
    $edit_script .= ($i<$n)?", ":"";
    $i++;
  }
  $edit_script .= "    ],
                        url: '/process/menu?p=update',
                        pk: ".$row->ID.",
                        success: function(result){  
                          var data = result.split(',');
                          $('#upby".$row->ID."').html(data[0]);
                          $('#updt".$row->ID."').html(data[1]); 
                        }  
                      });";
  $edit_script .= "  $('#TAX-".$row->ID."').editable({
                        url: '/process/menu?p=update',
                        pk: ".$row->ID.", 
                        validate: function(v) {
                          if (!v) return 'don\'t leave it blank!'; 
                          if (isNaN(v)) return 'please fill in a number format!';
                        },
                        success: function(result){  
                          var data = result.split(',');
                          $('#upby".$row->ID."').html(data[0]);
                          $('#updt".$row->ID."').html(data[1]); 
                      } 
                    });";
  }
  $edit_script .= "}); ";
	$edit_script .= '</script>';
  echo $edit_script;
?>
<script>   
$(document).ready(function()
{                    
  //make editable on focus  
  $('.edit').focus(function(e) {
    e.stopPropagation();
    $(this).editable('toggle');
  });
  
  var table = $('#setting').DataTable({
    columnDefs: [
      { targets: 'no-sort', orderable: false }
    ],
    "order": [[ 9, "desc" ]]
  });
  
  //check all
  $("#checkall").click(function(){
    $('.case').prop('checked',this.checked);
  });
  $(".case").click(function(){
    if($(".case").length==$(".case:checked").length){
      $("#selectall").prop("checked","checked");
    }else{
      $("#selectall").removeAttr("checked");
    }
  }); 
  
  //function to delete selected row
  $('.btn-danger').on("click", function(event){
  	var sel = false;	
  	var ch = $('#setting').find('tbody input[type=checkbox]');
    var dt = '';	
  	ch.each(function(){  
      if($(this).is(':checked')) { 
        var idf = $(this).parents('tr').attr('id');
        var idm = idf.substring(idf.indexOf('_')+1,idf.length);
  		  dt = dt+' '+idm+',';
      }    
    }); 
    if(dt==''){
      var c = false;
    } else {  	
  	  var c = confirm('Continue delete \n'+dt.substring(1,dt.length-1)+'?');
    }
  	if(c) {
  	  ch.each(function(){
  		 var $this = $(this);
  			if($this.is(':checked')) {
  				sel = true;	//set to true if there is/are selected row
          var idf = $(this).parents('tr').attr('id');
          var dataP = "idf="+idf;
  				$.ajax({
            type: "POST",
            url: "/process/menu?p=delete",
            data: dataP,
            cache: false,
            success: function(result){ 
              if(result.trim()!='OK'){    
                alert(result); 
              } else {    
        				$this.parents('tr').fadeOut(function(){
        					$this.remove(); //remove row when animation is finished
        				});     
              }   
            }
          });   
  			}
  	  });
  		  if(!sel) alert('No data selected');	
  	}
  	return false;
  }); 
});
  
</script>

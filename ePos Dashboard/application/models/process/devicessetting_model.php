<?php if ( ! defined('BASEPATH')) exit('No direct script access allowed');

class Devicessetting_model extends CI_Model {
  function __construct(){
    // Call the Model constructor
    parent::__construct();
  }
	 
	function get_profile(){
		$session_data = $this->session->userdata('logged_in');
		$id = $session_data['id'];
		$this->db->where('ID',$id);
    $query = $this->db->get('USERS');
    return $query->row();
  }  
	
	function update_devices($arrin){ 
	  date_default_timezone_set('Asia/Jakarta');
		$session_data = $this->session->userdata('logged_in');
		$id = $session_data['id']; 
		$dt = date('Y-m-d H:i:s');
		$up = strstr($arrin[2], '-', true);
	  $data = array(
               $up => $arrin[1],
               'LAST_UPDATED_BY' => $id,
               'LAST_UPDATED_DATE' => $dt,
            ); 
		$this->db->where('ID',$arrin[0]);
    $query = $this->db->update('DEVICES',$data);
    $output[0] = $this->process->get_username($this->process->get_devices($arrin[0])->LAST_UPDATED_BY);
    $output[1] = $this->process->get_devices($arrin[0])->LAST_UPDATED_DATE;
    $outputs = implode(",",$output);   
    return $outputs;
	}
	
	function delete_devices($cid){ 
		$did = strstr($cid, '_', true);
    $query = $this->db->where('ID', $did);
    $query = $this->db->limit(1);
    $query = $this->db->delete('DEVICES');
    if($this->db->affected_rows()!=0){
      $out = "OK";
    } else {                  
      $out = "Unable to delete";
    }
    return $out;
  }   
	
	function get_devices($cid){    
    $query = $this->db->select('*')
                      ->from('DEVICES')  
                      ->where('ID',$cid)
                      ->limit(1)
                      ->get('');
    return $query->row();
  }      
                     
	function get_restaurant_name($id){
    $query = $this->db->select('NAME AS REST_NAME')
                      ->from('RESTAURANTS')
                      ->where('ID',$id)
                      ->get('');
    return $query->row()->REST_NAME;
  }
  
  function get_username($id){
    $query = $this->db->select('USERNAME')
                      ->from('USERS')
                      ->where('ID',$id)
                      ->get('');
    return $query->row()->USERNAME;
  }
}
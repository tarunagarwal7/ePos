<?php if ( ! defined('BASEPATH')) exit('No direct script access allowed');

class Printersetting_model extends CI_Model {
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
	
	function update_printer($arrin){ 
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
    $query = $this->db->update('PRINTER',$data);
    $output[0] = $this->process->get_username($this->process->get_printer($arrin[0])->LAST_UPDATED_BY);
    $output[1] = $this->process->get_printer($arrin[0])->LAST_UPDATED_DATE;
    $outputs = implode(",",$output);   
    return $outputs;
	}
	
	function delete_printer($pid){ 
		$did = strstr($pid, '_', true); 
    $query = $this->db->where('ID', $did);
    $query = $this->db->limit(1);
    $query = $this->db->delete('PRINTER');    
    return $did;
  }
	
	function get_printer($pid){    
		$session_data = $this->session->userdata('logged_in');
		$id = $session_data['id'];
		$this->db->where('USERS_RESTAURANTS.USER_ID',$id);
    $query = $this->db->select('PRINTER.*')
                      ->from('PRINTER')
                      ->join('USERS_RESTAURANTS', 'PRINTER.REST_ID = USERS_RESTAURANTS.REST_ID')  
                      ->where('PRINTER.ID',$pid)
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
         
  function get_connectivity($code){
    $query = $this->db->select('VALUE')
                      ->from('REF_VALUES')
                      ->where('CODE',$code)
                      ->get('');
    return $query->row()->VALUE;
  }
  
}
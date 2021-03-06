<?php if ( ! defined('BASEPATH')) exit('No direct script access allowed');

class Users_model extends CI_Model {
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
  
  function get_restaurant(){
		$session_data = $this->session->userdata('logged_in');
		$id = $session_data['id'];
		$this->db->where('USERS_RESTAURANTS.USER_ID',$id);
    $query = $this->db->select('*')
                      ->from('RESTAURANTS')
                      ->join('USERS_RESTAURANTS', 'RESTAURANTS.ID = USERS_RESTAURANTS.REST_ID')
                      ->get('');
    return $query->result();
  }
    
	function get_username($id){
    $query = $this->db->select('USERNAME')
                      ->from('USERS')
                      ->where('ID',$id)
                      ->get('');
    return $query->row();
  }
  
	function get_restaurant_name($id){
    $query = $this->db->select('NAME AS REST_NAME')
                      ->from('RESTAURANTS')
                      ->where('ID',$id)
                      ->get('');
    return $query->row();
  }    
  
	function get_users_data(){ 
		$session_data = $this->session->userdata('logged_in');  
		$role = $session_data['role'];   
		if($role>1){
      $query = $this->db->where('ROLE_ID > 2');
    }
    $query = $this->db->get('USERS'); 
    return $query->result();
  } 
    
  function get_default_rest($user_id){                         
    $query = $this->db->query('SELECT USERS_RESTAURANTS.*, RESTAURANTS.NAME AS REST_NAME
                              FROM USERS_RESTAURANTS
                              JOIN RESTAURANTS ON USERS_RESTAURANTS.REST_ID = RESTAURANTS.ID
                              WHERE USERS_RESTAURANTS.DEFAULT_REST=1 AND USERS_RESTAURANTS.USER_ID = '.$user_id.';');
    $output = ($this->db->affected_rows()>0)?$query->row():"NONE";
    return $output;
  } 
  
  function get_assigned_rest($user_id){                         
    $query = $this->db->query('SELECT users_restaurants.REST_ID, restaurants.NAME AS NAME
                              FROM users_restaurants
                              JOIN restaurants ON users_restaurants.REST_ID = restaurants.ID
                              WHERE users_restaurants.USER_ID = '.$user_id.'
                              ');
    return $query->result();
  } 
  
  function get_rest_name0($rest_id){
    $query = $this->db->select('NAME')
                      ->from('restaurants')
                      ->where('REST_ID',$rest_id)
                      ->limit(1)
                      ->get('');   
    return $query->row()->REST_NAME; 
  }    
  
  function get_users_rest($user_id){                         
    $query = $this->db->query('SELECT USERS_RESTAURANTS.*, RESTAURANTS.NAME AS REST_NAME
                              FROM USERS_RESTAURANTS
                              JOIN RESTAURANTS ON USERS_RESTAURANTS.REST_ID = RESTAURANTS.ID
                              WHERE USERS_RESTAURANTS.USER_ID = '.$USER_ID.'
                              ');
    return $query->result();
  }
  
  function get_role_name($role_id){
    $query = $this->db->select('NAME')
                      ->from('ROLES')
                      ->where('ID',$role_id)
                      ->limit(1)
                      ->get('');
    return $query->row()->NAME;
  }
  
  function get_roles(){         
		$session_data = $this->session->userdata('logged_in');  
		$role = $session_data['role'];   
		if($role>1){
      $query = $this->db->where('ID > 2');
    }
    $query = $this->db->select('ID,NAME')
                      ->from('ROLES')
                      ->get('');
    return $query->result();
  }
   
	function new_users($NAME,$EMAIL,$USERNAME,$PASSWORD,$ROLE,$REST_ID){       
		$session_data = $this->session->userdata('logged_in');
		$PASSWD = sha1(md5($PASSWORD));
		$id = $session_data['id'];
    $query1 = $this->db->query('INSERT INTO USERS
      (NAME,EMAIL_ADDRESS,USERNAME,PASSWORD,ROLE_ID,CREATED_BY,CREATED_DATE,LAST_UPDATED_BY,LAST_UPDATED_DATE) 
      VALUES 
      ("'.$NAME.'","'.$EMAIL.'","'.$USERNAME.'","'.$PASSWD.'",'.$ROLE.','.$id.',NOW(),'.$id.',NOW());');
    $query2 = $this->db->query('INSERT INTO USERS_RESTAURANTS
      (USER_ID,REST_ID,DEFAULT_REST,CREATED_BY,CREATED_DATE,LAST_UPDATED_BY,LAST_UPDATED_DATE) 
      VALUES 
      ('.$this->setting->get_mail_user($EMAIL)->ID.','.$REST_ID.',1,'.$id.',NOW(),'.$id.',NOW());');
		//return $query->row();
  }
  
  function get_mail_user($EMAIL){        
    $query = $this->db->select('ID,NAME')
                      ->from('USERS')
                      ->where('EMAIL_ADDRESS',$EMAIL)
                      ->get('');
    return $query->row();
  }
}
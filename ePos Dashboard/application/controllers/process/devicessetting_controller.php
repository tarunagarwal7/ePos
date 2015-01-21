<?php if ( ! defined('BASEPATH')) exit('No direct script access allowed');

class Devicessetting_controller extends CI_Controller {
	
	function __construct()
	{
		parent::__construct();	
		$this->load->model('process/devicessetting_model','process',TRUE);
		$session_data = $this->session->userdata('logged_in');
		$this->data['user'] = $this->process->get_profile();
	}

	public function index()
	{
		if($this->session->userdata('logged_in'))
		{
			$data['menu'] = 'process';         
			$session_data = $this->session->userdata('logged_in'); 
      $data['p'] = $this->input->get('p');  
      if (isset($data['p'])) {   
        switch ($data['p']){
          case 'update':      
            $data['varP'] = $this->input->post('pk').','.$this->input->post('value').','.$this->input->post('name');  
            $this->load->view('process/update_devices',$data); 
          break;
          case 'delete':      
            $data['varP'] = $this->input->post('idf');
            $this->load->view('process/delete_devices',$data);
          break;
        }
      }
		}
		else
		{
			//If no session, redirect to login page
			redirect('login', 'refresh');
		}
		
	}
	
	public function logOn()
	{
		$this->load->view('login');
	}
	function logout()
	{
		$this->session->unset_userdata('logged_in');
		//session_destroy();
		redirect('dashboard', 'refresh');
	}
	
}

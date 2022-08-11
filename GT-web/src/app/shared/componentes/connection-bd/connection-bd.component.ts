import { Component, OnInit } from '@angular/core';
import { ConexionBD } from 'src/app/models/entities/ConexionBD';
import { ConexionBDService } from 'src/app/services/conexion-bd.service';
import Swal from 'sweetalert2';

@Component({
  selector: 'app-connection-bd',
  templateUrl: './connection-bd.component.html',
  styleUrls: ['./connection-bd.component.scss']
})
export class ConnectionBDComponent implements OnInit {
  selectedGestor: any[] = [];
  conexionBD: ConexionBD = {
    url: '',
    usuario: '',
    contrasena: '',
    baseDatos: ''
  };

  selectedBD: string;
  txtCadena: string;
  txtUsuario: string;
  txtPass: string;

  //Variables auxiliares para controlar los eventos
  public check: string;
  public load: boolean;
  constructor(private serviceConexion: ConexionBDService) {
    this.check = 'assets/recursos/check.png';
    this.load = false;
  }

  ngOnInit(): void {
    this.selectedGestor = [
      { id: 'oracle', base: 'Oracle', diver: 'oracle.jdbc.driver.OracleDriver' },
      { id: 'postgres', base: 'Postgres', diver: 'org.postgresql.Driver' }
    ]
  }


  setConexion() {
    this.conexionBD.baseDatos = this.selectedBD;
    this.conexionBD.url = this.txtCadena;
    this.conexionBD.usuario = this.txtUsuario;
    this.conexionBD.contrasena = this.txtPass;
  }

  conexionPrueba() {
    this.setConexion();
    this.serviceConexion.conexionBD(this.conexionBD).subscribe({
      next: (result: any) => {
        //this.load = result.data;
        Swal.mixin({
          toast: true,
          position: 'top-end',
          showConfirmButton: false,
          timer: 3000,
          timerProgressBar: true,
          didOpen: (toast) => {
            toast.addEventListener('mouseenter', Swal.stopTimer)
            toast.addEventListener('mouseleave', Swal.resumeTimer)
          }
        }).fire({
          icon: 'success',
          title: 'Conexión exitosa'
        })
      },
      error: (error) => {
        "Error: " + console.log(error)
      }
    })
  }

}
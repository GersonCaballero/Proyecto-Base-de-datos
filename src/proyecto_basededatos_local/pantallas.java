/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyecto_basededatos_local;

import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author gerso_000
 */
public class pantallas {
    Consultas con = new Consultas();
    correo corre = new correo();
    Scanner sc=new Scanner(System.in);
    String corr;
    
    public boolean validateEmail(String user) {
        
        String emailRegex = "^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$";
        Pattern emailPat = Pattern.compile(emailRegex, Pattern.CASE_INSENSITIVE);
        Matcher matcher = emailPat.matcher(user);
        if (matcher.find()){
            return true;
        }else{
        System.out.println("Correo electronico invalido");
            return false;
        }
        
       
    }
    
    void pantalla1(){ 
    int cont=0;
    String contrasena;
    String rs;
    String r;
    
    
        System.out.println("**********************************************************************");
        System.out.println("BIENVENIDO");
        do{
        System.out.println("Ingrese su correo electronico");
        String correo=sc.nextLine();
        corr = correo;
        }while(this.validateEmail(corr)!=true);
        do{
        System.out.println("Ingrese contraseña");
        String conta=sc.nextLine();
        rs=con.getConsultaIngreso(corr, conta);
        cont++;
        if(cont==3){
        corre.enviar(corr, "SEGURIDAD DE CUENTA", "Buen día, solo como medida de seguridad te avisamos que alguien esta intentando entrar a tu cuenta.");
        this.pantalla2();
        }
        }while(!rs.equals(corr));
        System.out.println("**********************************************************************");
        if (rs.equals(corr)){
        pantalla3();
        }
        else{
        pantalla2();
        }
    }
    
    void pantalla2(){ 
        System.out.println("**********************************************************************");
        System.out.println("Usuraio incorrecto");
        System.out.println("quiere crear un usuario?s/n");
        String nuevo=sc.nextLine();
        if (nuevo.equals("s")){
        System.out.println("Ingrese la siguiente información");
        System.out.println("Ingrese correo");
        String correo=sc.nextLine();
        System.out.println("Ingrese contraseña");
        String contrasena=sc.nextLine();
        System.out.println("Ingrese Y si va a guardar N si va a cancelar");
        String respuesta=sc.nextLine();
        if (respuesta.equals("y")){
        con.insertUsuarios(correo, contrasena);
        this.pantalla1();
        }
        else{
        System.out.println("El correo no fue guardado");
        this.pantalla1();
        }
        }
        else{
        this.pantalla1();
        }
        System.out.println("**********************************************************************");
    }
    
    void pantalla3(){
        System.out.println("MENÚ");
        System.out.println("1. CREAR PROYECTOS");
        System.out.println("2. VER PROYECTOS");
        System.out.println("3. CREAR TAREAS DE PROYECTOS");
        System.out.println("4. VER TAREAS");
        System.out.println("5. COMPARTIR");
        System.out.println("6. SALIR");
        System.out.println("INGRESE UNA OPCION");
        int opcion=sc.nextInt();
        sc.nextLine();
        
        switch(opcion){
            case 1:
                pantalla4();
                break;
            case 2:
                pantalla5();
                break;
            case 3:
                pantalla6();
                break;
            case 4:
                pantalla7();
                break;
            case 5:
                pantalla8();
                break;
            case 6:
                pantalla1();
                break;
        }
    }
    
    void pantalla4(){
        System.out.println("CREAR PROYECTO");
        System.out.println("INGRESE CODIGO DE PROYECTO");
        String codigo=sc.nextLine();
        System.out.println("DESCRIPCIÓN DE PROYECTO");
        String descripcion=sc.nextLine();
        System.out.println("FECHA DE INICIO");
        String finicio=sc.nextLine();
        System.out.println("FECHA FINAL");
        String ffinal=sc.nextLine();
        System.out.println("--------------------------LISTA DE EMPLEADOS--------------------------");
        System.out.println(con.getConsultaUsuarios());
        System.out.println("----------------------------------------------------------------------");
        System.out.println("RESPONSABLE");
        String responsable=sc.nextLine();
        String rs=con.getConsultaid(responsable);
        System.out.println("ID: "+rs);
        con.insertProyectos(codigo, descripcion, finicio, ffinal, responsable, rs);
        String conc="Descripcion de proyecto"+descripcion+" "+"con una fecha de inicio del "+finicio+" y con fecha limite "+ffinal;
        corre.enviar(responsable, "ASIGNACIÓN DE UN PROYECTO", conc);
        System.out.println("--------------------------GUARDADO CON EXITO--------------------------");
        pantalla3();
    }
    
    void pantalla5(){
        System.out.println(con.getConsultaProyectosEmpleado(corr));
        pantalla3();
    }
    
    void pantalla6(){
        System.out.println("ASIGNAR TAREAS");
        System.out.println("--------------------------LISTA DE PROYECTOS--------------------------");
        System.out.println(con.getConsultaProyectos());
        System.out.println("----------------------------------------------------------------------");
        System.out.println("INGRESE CODIGO DE PROYECTO");
        String codigo=sc.nextLine();
        String ds=con.getConsultaidpro(codigo);
        System.out.println("ID DE PROYECTO: "+ds);
        System.out.println("DESCRIPCIÓN");
        String descripcion=sc.nextLine();
        System.out.println("FECHA DE ENTREGA");
        String fecha=sc.nextLine();
        System.out.println("OBSERVACIONES");
        String observaciones=sc.nextLine();
        System.out.println("PROCENTAJE DE TAREA EN PROYECTO");
        int porcentaje=sc.nextInt();
        System.out.println("ESTATUS");
        int estatus=sc.nextInt();
        System.out.println("TIPO DE TAREA");
        int tipo=sc.nextInt();
        System.out.println("NIVEL DE PRIORIDAD");
        int prioridad=sc.nextInt();
        sc.nextLine();
        System.out.println("--------------------------LISTA DE EMPLEADOS--------------------------");
        System.out.println(con.getConsultaUsuarios());
        System.out.println("----------------------------------------------------------------------");
        System.out.println("RESPONSABLE");
        String responsable=sc.nextLine();
        String rs=con.getConsultaid(responsable);
        System.out.println("ID: "+rs);
        con.insertTareas(ds, descripcion, fecha, observaciones, porcentaje, estatus, tipo, prioridad, responsable, rs);
        String conc="Descripcion de tarea"+descripcion+" "+"con una prioridad nivel "+prioridad+"con una fecha limite de"+fecha;
        corre.enviar(responsable, "ASIGNACIÓN DE UNA TAREA", conc);
        System.out.println("--------------------------GUARDADO CON EXITO--------------------------");
        pantalla3();
    }
    
    void pantalla7(){
       System.out.println(con.getConsultaTareasEmpleado(corr));
       pantalla3();
    }
    
    void pantalla8(){
        System.out.println("--------------------------LISTA DE TAREAS--------------------------");
        System.out.println(con.getConsultaTareasEmpleado(corr));
        System.out.println("----------------------------------------------------------------------");
        System.out.println("INGRESE EL ID DE LA TAREA");
        String id=sc.nextLine();
        String proid=con.getConsultaidproyectotareas(id);
        String des=con.getConsultadescripciontareas(id);
        String fec=con.getConsultafechatareas(id);
        String obs=con.getConsultaobservacionestareas(id);
        String por=con.getConsultaporcentajetareas(id);
        int porce = Integer.parseInt(por);
        String est=con.getConsultaestatustareas(id);
        int esta = Integer.parseInt(est);
        String tip=con.getConsultatipotareas(id);
        int tipo = Integer.parseInt(tip);
        String pri=con.getConsultaprioridadtareas(id);
        int prio = Integer.parseInt(pri);
        System.out.println("PROYECTOID: "+proid);
        System.out.println("DESCRIPCION: "+des);
        System.out.println("FECHA: "+fec);
        System.out.println("OBSERVACIONES: "+obs);
        System.out.println("PORCENTAJE: "+por);
        System.out.println("ESTATUS: "+est);
        System.out.println("TIPO: "+tip);
        System.out.println("PRIORIDAD: "+pri);
        System.out.println("--------------------------LISTA DE EMPLEADOS--------------------------");
        System.out.println(con.getConsultaUsuarios());
        System.out.println("----------------------------------------------------------------------");
        System.out.println("INGRESE EL CORREO DE LA PERSONA CON QUIEN QUIERE COMPARTIR EL PROYECTO");
        String compartir=sc.nextLine();
        String usu=con.getConsultaid(compartir);
        con.insertTareas(proid, des, fec, obs, porce, esta, tipo, prio, compartir, usu);
        String conc="Se te ha compartido una tarea, Descripcion de tarea "+des+" con una prioridad nivel "+pri+" con una fecha limite de"+fec;
        corre.enviar(compartir, "ASIGNACIÓN DE UNA TAREA", conc);
        System.out.println("-------------------------COMPARTIDO CON EXITO-------------------------");
        pantalla3();
    }
}

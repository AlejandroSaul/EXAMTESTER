select * from preguntas where pregunta like ('%nce will the characters%');
select * from preguntas where ID_SUBTEMA_TOPICO = 86; #Manejo de Fecha
select * from preguntas; where ID_PREGUNTA = 926;
delete from tema where id_tema =  3;
select * from subtema_topico st 
join subtema s on s.id_subtema = st.id_subtema
join topico t on t.id_topico = st.id_topico
join tema te on te.ID_TEMA = s.ID_TEMA where NOMBRE_TOPICO like ('%UDEM%') and NOMBRE_SUBTEMA like ('%Arr%');

select * from subtema_topico st 
join subtema s on s.id_subtema = st.id_subtema
join topico t on t.id_topico = st.id_topico
join tema te on te.ID_TEMA = s.ID_TEMA; where id_subtema_topico = 102;

delete from preguntas where id_pregunta > 586;
delete from subtema_topico where id_topico in (106,107,108);
delete from topico where id_topico in (106,107,108);
delete from subtema where id_subtema > 7 ;

select * from topico;
select * from subtema;
select * from tema;
select * from origen;

insert into subtema (id_subtema,id_tema,Nombre_subtema) values (8,2,'Curso Practico de formación IZO-808');
insert into subtema (id_subtema,id_tema,Nombre_subtema) values (9,2,'Oracle Certified Associate Sybex');
insert into subtema (id_subtema,id_tema,Nombre_subtema) values (10,2,'Certification Exams - Oracle Certified Associate (OCA) UDEMY');

commit;
SET SQL_SAFE_UPDATES = 0;
UPDATE preguntas SET id_origen = 3 WHERE id_origen = 6;
SET SQL_SAFE_UPDATES = 1;




SELECT 
	P.ID_PREGUNTA, P.PREGUNTA, 
    P.RESPUESTA_A, P.RESPUESTA_B, 
    P.RESPUESTA_C, P.RESPUESTA_D, 
    P.RESPUESTA_E, P.RESPUESTA_F, 
    P.RESPUESTA_G, P.RESPUESTA_H, 
    P.RESPUESTA_I, P.RESPUESTA_CORRECTA#, 
    #ORI.ORIGEN, S.NOMBRE_SUBTEMA, 
    #T.NOMBRE_TEMA, TOP.NOMBRE_TOPICO, 
   # M.NOMBRE_MATERIA, P.UNIDAD, P.EXPLICACION 
FROM preguntas P 
	#JOIN origen ORI ON ORI.ID_ORIGEN = P.ID_ORIGEN 
    JOIN subtema_topico ST ON ST.ID_SUBTEMA_TOPICO = P.ID_SUBTEMA_TOPICO 
    JOIN subtema S ON ST.ID_SUBTEMA = S.ID_SUBTEMA 
    JOIN TEMA T ON T.ID_TEMA = S.ID_TEMA 
    JOIN TOPICO TOP ON TOP.ID_TOPICO = ST.ID_TOPICO 
    LEFT JOIN MATERIA M ON P.ID_MATERIA = M.ID_MATERIA WHERE P.ID_PREGUNTA = 934;



where nombre_subtema like '%Fec%';
where st.ID_SUBTEMA_TOPICO = 86;

select * from subtema_topico st 
join subtema s on s.id_subtema = st.id_subtema
join topico t on t.id_topico = st.id_topico
join tema te on te.ID_TEMA = s.ID_TEMA;

UPDATE preguntas SET ID_SUBTEMA_TOPICO = 84 where ID_pregunta = 675;
COMMIT;
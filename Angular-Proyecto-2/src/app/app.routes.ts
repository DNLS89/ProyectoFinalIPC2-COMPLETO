import { Routes } from '@angular/router';
import { LogInComponent } from './Ventanas/log-in/log-in.component';
import { MenuPrincipalComponent } from './Ventanas/menu-principal/menu-principal.component';
import { PageNotFoundComponent } from './page-not-found/page-not-found.component';
import { CrearCuentaComponent } from './Cuenta/crear-cuenta/crear-cuenta.component';
import { EditarPerfilComponent } from './Cuenta/editar-perfil/editar-perfil.component';
import { PerfilAutorComponent } from './Cuenta/perfil-autor/perfil-autor.component';
import { ExplorarRevistasComponent } from './Explorador Revistas/explorar-revistas/explorar-revistas.component';
import { RevistasPublicadasComponent } from './Explorador Revistas/revistas-publicadas/revistas-publicadas.component';
import { PublicarRevistaComponent } from './Gestion Revistas/publicar-revista/publicar-revista.component';
import { TiendaComponent } from './tienda/tienda.component';
import { SuscribirComponent } from './Gestion Revistas/suscribir/suscribir.component';
import { RevistasSuscritasComponent } from './Explorador Revistas/revistas-suscritas/revistas-suscritas.component';
import { GestorAnunciosComponent } from './Anuncios/gestor-anuncios/gestor-anuncios.component';
import { CrearAnuncioComponent } from './Anuncios/crear-anuncio/crear-anuncio.component';
import { ComprarAnuncioComponent } from './Anuncios/comprar-anuncio/comprar-anuncio.component';
import { CambiarEstadoAnuncioComponent } from './Anuncios/cambiar-estado-anuncio/cambiar-estado-anuncio.component';
import { ModificarComponent } from './Anuncios/modificar/modificar.component';
import { CambiarEstadoAuncioAnunciadorComponent } from './Anuncios/cambiar-estado-auncio-anunciador/cambiar-estado-auncio-anunciador.component';
import { RevistaIndividualCompletaComponent } from './Explorador Revistas/revista-individual-completa/revista-individual-completa.component';
import { PruebaRevistaCompletaComponent } from './Explorador Revistas/prueba-revista-completa/prueba-revista-completa.component';
import { GestorReportesAdminComponent } from './Reportes Admin/gestor-reportes-admin/gestor-reportes-admin.component';
import { GestorReportesAutorComponent } from './Reportes Autor/gestor-reportes-autor/gestor-reportes-autor.component';
import { ReporteGananciasComponent } from './Reportes Admin/reporte-ganancias/reporte-ganancias.component';
import { ReporteAnunciosCompradosComponent } from './Reportes Admin/reporte-anuncios-comprados/reporte-anuncios-comprados.component';
import { ReporteGanaciasPorAnuncianteComponent } from './Reportes Admin/reporte-ganacias-por-anunciante/reporte-ganacias-por-anunciante.component';
import { ReporteRevistasPopularesComponent } from './Reportes Admin/reporte-revistas-populares/reporte-revistas-populares.component';
import { ReporteRevistasMasComentadasComponent } from './Reportes Admin/reporte-revistas-mas-comentadas/reporte-revistas-mas-comentadas.component';
import { ReporteEfectividadComponent } from './Reportes Admin/reporte-efectividad/reporte-efectividad.component';
import { ReporteComentariosComponent } from './Reportes Autor/reporte-comentarios/reporte-comentarios.component';
import { ReporteSuscripcionesComponent } from './Reportes Autor/reporte-suscripciones/reporte-suscripciones.component';
import { ReporteRevistasMasGustadasComponent } from './Reportes Autor/reporte-revistas-mas-gustadas/reporte-revistas-mas-gustadas.component';
import { ReportePagosComponent } from './Reportes Autor/reporte-pagos/reporte-pagos.component';

export const routes: Routes = [
    { path: '', redirectTo: '/login', pathMatch: 'full'},
    { path: 'crearCuenta', component: CrearCuentaComponent},
    { path: 'login', component: LogInComponent },
    { path: 'perfilAutor', component: PerfilAutorComponent },  
    { path: 'menu', component: MenuPrincipalComponent, 
        children:[
            { path: 'editarPerfil', component: EditarPerfilComponent},
            { path: 'explorarRevistas', component: ExplorarRevistasComponent},
            { path: 'revistasPublicadas', component: RevistasPublicadasComponent},
            { path: 'revistasSuscritas', component: RevistasSuscritasComponent},
            { path: 'verRevistaSuscrita/:numero', component: PruebaRevistaCompletaComponent},
            { path: 'reportesAdmin', component: GestorReportesAdminComponent,
                children: [
                    { path: 'ganancias', component: ReporteGananciasComponent},
                    { path: 'anunciosComprados', component: ReporteAnunciosCompradosComponent},
                    { path: 'gananciasPorAnunciante', component: ReporteGanaciasPorAnuncianteComponent},
                    { path: 'revistasMasPopulares', component: ReporteRevistasPopularesComponent},
                    { path: 'revistasMasComentadas', component: ReporteRevistasMasComentadasComponent},
                    { path: 'efectividadAnuncios', component: ReporteEfectividadComponent},
                ]
            },
            { path: 'reportesAutor', component: GestorReportesAutorComponent,
                children: [
                    { path: 'comentarios', component: ReporteComentariosComponent},
                    { path: 'suscripciones', component: ReporteSuscripcionesComponent},
                    { path: 'revistasMasGustadas', component: ReporteRevistasMasGustadasComponent},
                    { path: 'pagosHechos', component: ReportePagosComponent},
                ]
            },
            { path: 'publicarRevista', component: PublicarRevistaComponent},
            { path: 'tienda', component: TiendaComponent},
            { path: 'gestorAnuncios', component: GestorAnunciosComponent,
                children: [
                    { path: 'crearAnuncio', component: CrearAnuncioComponent},
                    { path: 'modificarAnuncios', component: ModificarComponent},
                    { path: 'estadoAnuncio', component: CambiarEstadoAnuncioComponent},
                    { path: 'estadoAnuncioAnunciador', component: CambiarEstadoAuncioAnunciadorComponent},
                    { path: 'comprarAnuncio', component: ComprarAnuncioComponent},
                ]
            },
        ]},
    { path: '**', component: PageNotFoundComponent}
    


];

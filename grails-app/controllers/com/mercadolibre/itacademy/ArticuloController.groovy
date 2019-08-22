package com.mercadolibre.itacademy

import grails.converters.JSON
import grails.rest.RestfulController

class ArticuloController extends RestfulController<Articulo> {

    static responseFormats = ['json']

    ArticuloController() {
        super(Articulo)
    }

    def index() {

        def marcaId = params.marcaId
        if(marcaId != null) {
            render (Articulo.where {
                marca.id == marcaId
            }.list() as JSON)
        } else {
            render(Marca.list() as JSON)
        }

    }

}
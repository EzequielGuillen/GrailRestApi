package com.mercadolibre.itacademy

import grails.converters.JSON
import groovy.json.JsonSlurper

import java.text.SimpleDateFormat

class BootStrap {

    def init = { servletContext ->

        def hotel1 = new Hotel(name: 'Hotel Vista').save(flush:true)
        def hotel2 = new Hotel(name: 'Premium Tower').save(flush:true)
        hotel1.addToRooms(new Room(number: 201)).save()
        hotel1.addToRooms(new Room(number: 202)).save()
        hotel1.addToRooms(new Room(number: 203)).save()
        hotel2.addToRooms(new Room(number: 301)).save()
        hotel2.addToRooms(new Room(number: 302)).save()
        hotel2.addToRooms(new Room(number: 303)).save()


        def marca1 = new Marca(name: 'Eze1').save(flush:true)
        def marca2 = new Marca(name: 'Eze2').save(flush:true)
        def marca3 = new Marca(name: 'Eze3').save(flush:true)

        marca1.addToArticulo(new Articulo(name: 'Zapatillas del rayo macqueen',picture: 'https://http2.mlstatic.com/zapatillas-cars-c-luces-rayo-mcqueen-nunez-D_NQ_NP_997423-MLA31063289864_062019-Q.jpg', total_items_in_this_category:  5)).save()
        marca1.addToArticulo(new Articulo(name: 'Cortos de Boca',picture: 'https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcRNRjMnPGw2tk7f-cKXS5-0ql1V4autbQEGz5OtiYuI9IS3WmIvSw',total_items_in_this_category:  10)).save()
        marca1.addToArticulo(new Articulo(name: 'botella ML',picture: 'https://http2.mlstatic.com/D_NQ_NP_750322-MLA31085361258_062019-V.jpg',total_items_in_this_category:  30)).save()
        marca2.addToArticulo(new Articulo(name: 'Lentes para ver',picture: 'https://images-na.ssl-images-amazon.com/images/I/71iSOwAoqyL._SX425_.jpg',total_items_in_this_category:  97)).save()
        marca2.addToArticulo(new Articulo(name: 'Taza Mercado Libre',picture: 'https://http2.mlstatic.com/sistema-solar-unico-mapa-mundo-tazas-de-agua-caliente-cera-D_NQ_NP_823647-MLA30972102541_062019-Q.jpg', total_items_in_this_category:  256)).save()
        marca2.addToArticulo(new Articulo(name: 'Funda telefono',picture: 'https://circulonatural.com/wp-content/uploads/2019/03/400-x-536-Funda-Celular-Siena-iPhone.jpg',total_items_in_this_category:  88)).save()
        marca3.addToArticulo(new Articulo(name: 'Mac Book Pro',picture: '',total_items_in_this_category:  1)).save()
        marca3.addToArticulo(new Articulo(name: 'cuaderno Mercado Libre',picture: '', total_items_in_this_category:  10)).save()
        marca3.addToArticulo(new Articulo(name: 'yoghurt',picture: '',total_items_in_this_category:  30)).save()



        marshaller()

    }

    def destroy = {



    }

    private void marshaller(){

        JSON.registerObjectMarshaller(Marca){
            marca -> [
                    id: marca.id,
                    name: marca.name,
            ]
        }

        JSON.registerObjectMarshaller(Articulo){

            articulo -> [
                    id: articulo.id,
                    name: articulo.name,
                    picture: articulo.picture,
                    total_items_in_this_category: articulo.total_items_in_this_category,
                    children_categories: []
            ]

        }

        JSON.registerObjectMarshaller(Hotel) {
            hotel -> [
                id: hotel.id,
                name: hotel.name,
                rooms: hotel.rooms.collect {
                    room -> [
                            id: room.id,
                            number: room.number
                    ]
                }
            ]
        }

        JSON.registerObjectMarshaller(Room) {
            room-> [
                    id: room.id,
                    number: room.number,
                    date: new SimpleDateFormat("dd/MM/yyyy").format(new Date())
            ]
        }


    }

}

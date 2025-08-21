package com.parquesoftti.panaderia.exception;

public class ProductoNotFoundException extends RuntimeException {
        public ProductoNotFoundException(String message) {
            super(message);
        }
    }
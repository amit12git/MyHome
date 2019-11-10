package com.myhome.cassandra;


import org.springframework.data.cassandra.repository.CassandraRepository;
import org.springframework.stereotype.Service;


public  interface MyHomeRepository extends CassandraRepository<MyHome, String> {

}

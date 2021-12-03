[12:33] Antoine FISSOT


SessionFactory sessionFactory; 
        final StandardServiceRegistry registry =
                new StandardServiceRegistryBuilder().configure().build();   
        sessionFactory = new MetadataSources(registry)
                        .buildMetadata().buildSessionFactory();
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        Product p1 = new Product();
        p1.setName("Porsche");
        p1.setPrice(345000);
        ProductDetail productDetail = new ProductDetail();
        productDetail.setOrigin("DE");
        productDetail.setWeight(2000);
        productDetail.setPart_number("GREHRTESX");
        productDetail.setProduct(p1);
        
        session.save(productDetail);
        List<ProductDetail> products = session.createQuery("from ProductDetail").list();
        for (ProductDetail product: products) {
            StringBuilder info = new StringBuilder();
            info.append("Produit : " + product.getProduct().getName() + "\r");
            info.append("__________________");
            System.out.println(info.toString());
        }
        session.getTransaction().commit();
        session.close();
        sessionFactory.close();
    }
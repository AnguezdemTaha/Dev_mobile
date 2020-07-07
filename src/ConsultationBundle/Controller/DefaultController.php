<?php

namespace ConsultationBundle\Controller;

use Symfony\Bundle\FrameworkBundle\Controller\Controller;
use Symfony\Component\HttpFoundation\Request;
use Symfony\Component\Routing\Annotation\Route;

class DefaultController extends Controller
{
    public function indexAction()
    {
        return $this->render('ConsultationBundle:Default:base.html.twig');
    }

 


  
    public function Conscreate(Request $request,EntityManagerInterface $manager)
    {
        $cons = new Consultation();
        $cons->setDate(new  \DateTime());
        $form = $this->createFormBuilder($cons)
                    ->add('idd')
                    ->add('idp')
                    ->add('heure')
                    ->add('date')
                    ->getForm();
        $form->handleRequest($request);
        if($form->isSubmitted() && $form->isValid()){
            
            $manager->persist($cons);
            $manager->flush();
            unset($cons);
            unset($form);
            $cons = new Consultation();
            $cons->setDate(new  \DateTime());
            $form = $this->createFormBuilder($cons)
            ->add('idd')
            ->add('idp')
            ->add('heure')
            ->add('date')
            ->getForm();
        }
        return $this->render('const/create.html.twig',[
            'formCons'=> $form->createView()
            ]);
    }

}
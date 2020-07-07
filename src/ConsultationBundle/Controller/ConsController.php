<?php

namespace ConsultationBundle\Controller;

use ConsultationBundle\Entity\Cons;
use Symfony\Bundle\FrameworkBundle\Controller\Controller;
use Symfony\Component\HttpFoundation\Request;

/**
 * Con controller.
 *
 */
class ConsController extends Controller
{
    /**
     * Lists all con entities.
     *
     */
    public function indexAction()
    {
        $em = $this->getDoctrine()->getManager();

        $cons = $em->getRepository('ConsultationBundle:Cons')->findAll();

        return $this->render('cons/index.html.twig', array(
            'cons' => $cons,
        ));
    }

    /**
     * Creates a new con entity.
     *
     */
    public function newAction(Request $request)
            {
            
            $cons=new Cons();
            $cons->setDate(new  \DateTime());
            $form=$this->createForm('ConsultationBundle\Form\ConsType',$cons);
            $form->handleRequest($request);
            if($form->isSubmitted() && $form->isValid()){
                $em=$this->getDoctrine()->getManager();
                $em->persist($cons);
                $em->flush($cons);
                unset($cons);
                unset($form);
                $cons = new Cons();
                $cons->setDate(new  \DateTime());
                $form=$this->createForm('ConsultationBundle\Form\ConsType',$cons);
                
            }

            return $this->render('cons/new.html.twig',array(
                'cons'=>$cons,
                'formCons'=>$form->CreateView(),
            )) ;   
            
    }

    /**
     * Finds and displays a con entity.
     *
     */
    public function showAction(Cons $con)
    {
        $deleteForm = $this->createDeleteForm($con);

        return $this->render('cons/show.html.twig', array(
            'con' => $con,
            'delete_form' => $deleteForm->createView(),
        ));
    }

    /**
     * Displays a form to edit an existing con entity.
     *
     */
    public function editAction(Request $request, Cons $con)
    {
        $deleteForm = $this->createDeleteForm($con);
        $editForm = $this->createForm('ConsultationBundle\Form\ConsTypedite', $con);
        $editForm->handleRequest($request);

        if ($editForm->isSubmitted() && $editForm->isValid()) {
            $this->getDoctrine()->getManager()->flush();

            return $this->redirectToRoute('cons_edit', array('id' => $con->getId()));
        }

        return $this->render('cons/edit.html.twig', array(
            'con' => $con,
            'edit_form' => $editForm->createView(),
            'delete_form' => $deleteForm->createView(),
        ));
    }

    /**
     * Deletes a con entity.
     *
     */
    public function deleteAction(Request $request, Cons $con)
    {
        $form = $this->createDeleteForm($con);
        $form->handleRequest($request);

        if ($form->isSubmitted() && $form->isValid()) {
            $em = $this->getDoctrine()->getManager();
            $em->remove($con);
            $em->flush();
        }

        return $this->redirectToRoute('cons_index');
    }

    /**
     * Creates a form to delete a con entity.
     *
     * @param Cons $con The con entity
     *
     * @return \Symfony\Component\Form\Form The form
     */
    private function createDeleteForm(Cons $con)
    {
        return $this->createFormBuilder()
            ->setAction($this->generateUrl('cons_delete', array('id' => $con->getId())))
            ->setMethod('DELETE')
            ->getForm()
        ;
    }
}

<?php

namespace ConsultationBundle\Controller;

use ConsultationBundle\Entity\Docteur;
use Symfony\Bundle\FrameworkBundle\Controller\Controller;
use Symfony\Component\HttpFoundation\Request;

/**
 * Docteur controller.
 *
 */
class DocteurController extends Controller
{
    /**
     * Lists all docteur entities.
     *
     */
    public function indexAction()
    {
        $em = $this->getDoctrine()->getManager();

        $docteurs = $em->getRepository('ConsultationBundle:Docteur')->findAll();

        return $this->render('docteur/index.html.twig', array(
            'docteurs' => $docteurs,
        ));
    }

    /**
     * Creates a new docteur entity.
     *
     */
    public function newAction(Request $request)
    {
        $docteur = new Docteur();
        $form = $this->createForm('ConsultationBundle\Form\DocteurType', $docteur);
        $form->handleRequest($request);

        if ($form->isSubmitted() && $form->isValid()) {
            $em = $this->getDoctrine()->getManager();
            $em->persist($docteur);
            $em->flush();

            return $this->redirectToRoute('docteur_show', array('id' => $docteur->getId()));
        }

        return $this->render('docteur/new.html.twig', array(
            'docteur' => $docteur,
            'form' => $form->createView(),
        ));
    }

    /**
     * Finds and displays a docteur entity.
     *
     */
    public function showAction(Docteur $docteur)
    {
        $deleteForm = $this->createDeleteForm($docteur);

        return $this->render('docteur/show.html.twig', array(
            'docteur' => $docteur,
            'delete_form' => $deleteForm->createView(),
        ));
    }

    /**
     * Displays a form to edit an existing docteur entity.
     *
     */
    public function editAction(Request $request, Docteur $docteur)
    {
        $deleteForm = $this->createDeleteForm($docteur);
        $editForm = $this->createForm('ConsultationBundle\Form\DocteurType', $docteur);
        $editForm->handleRequest($request);

        if ($editForm->isSubmitted() && $editForm->isValid()) {
            $this->getDoctrine()->getManager()->flush();

            return $this->redirectToRoute('docteur_edit', array('id' => $docteur->getId()));
        }

        return $this->render('docteur/edit.html.twig', array(
            'docteur' => $docteur,
            'edit_form' => $editForm->createView(),
            'delete_form' => $deleteForm->createView(),
        ));
    }

    /**
     * Deletes a docteur entity.
     *
     */
    public function deleteAction(Request $request, Docteur $docteur)
    {
        $form = $this->createDeleteForm($docteur);
        $form->handleRequest($request);

        if ($form->isSubmitted() && $form->isValid()) {
            $em = $this->getDoctrine()->getManager();
            $em->remove($docteur);
            $em->flush();
        }

        return $this->redirectToRoute('docteur_index');
    }

    /**
     * Creates a form to delete a docteur entity.
     *
     * @param Docteur $docteur The docteur entity
     *
     * @return \Symfony\Component\Form\Form The form
     */
    private function createDeleteForm(Docteur $docteur)
    {
        return $this->createFormBuilder()
            ->setAction($this->generateUrl('docteur_delete', array('id' => $docteur->getId())))
            ->setMethod('DELETE')
            ->getForm()
        ;
    }
}

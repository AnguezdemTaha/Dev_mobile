<?php

namespace AppBundle\Controller;

use AppBundle\AppBundle;
use Symfony\Bundle\FrameworkBundle\Controller\Controller;
use Sensio\Bundle\FrameworkExtraBundle\Configuration\Route;
use Symfony\Component\Form\Extension\Core\Type\SubmitType;
use Symfony\Component\Security\Http\Authentication\AuthenticationUtils;
use Symfony\Component\HttpFoundation\Request;
use AppBundle\Entity\Member;
use Symfony\Component\Form\Extension\Core\Type\TextType;

class SecurityController extends Controller
{
    /**
     * @Route("/login", name="login")
     */
    public function loginAction(){
        return $this->render('security/login.html.twig',[
        ]);
    }

    /**
     * @Route("/logout")
     */
    public function logoutAction(){
        throw new \RuntimeException("This should never be called directly.");
    }

    /**
     * @Route("/cons_path", name="cons_path")
     */
    public function postLoginRedirectAction()
    {
        return $this->redirectToRoute("cons_index");
    }

    /**
     * @Route("/user", name="view_user_route")
     */
    public function viewUserAction(){
        $users = $this->getDoctrine()->getRepository('AppBundle:Member')->findAll();
        return $this->render("users/user.html.twig",['users'=>$users]);
    }

    /**
     * @Route("/user/createuser", name="create_user_route")
     */
    public function createUserAction(Request $request){
        $user = new Member;
        $form = $this->createFormBuilder($user)
            ->add('username',TextType::Class, array('attr'=>array('class'=>'form-control')))
            ->add('email',TextType::Class, array('attr'=>array('class'=>'form-control')))
            ->add('password',TextType::Class, array('attr'=>array('class'=>'form-control')))
            ->add('save',SubmitType::Class, array('label'=>'create User', 'attr'=> array('class' =>'btn btn-primary')))
            ->getForm();
        $form->handleRequest($request);
        if ($form->isSubmitted()&&$form->isValid()){
            $username = $form['username']->getData();
            $email = $form['email']->getData();
            $password = $form['password']->getData();

            $user->setUsername($username);
            $user->setEmail($email);
            $user->setPassword($password);

            $em = $this->getDoctrine()->getManager();
            $em->persist($user);
            $em->flush();
            $this->addFlash('message','User saved');
            return $this->redirectToRoute('view_user_route');
        }
        return $this->render("users/createuser.html.twig",[
            'form' => $form->createView()
        ] );
    }

    /**
     * @Route("/user/updateuser/{id}", name="update_user_route")
     */
    public function updateUserAction($id, Request $request){
        $user = $this->getDoctrine()->getRepository('AppBundle:Member')->find($id);
        $user->setUsername($user->getUsername());
        $user->setEmail($user->getEmail());
        $user->setPassword($user->getPassword());

        $form = $this->createFormBuilder($user)
            ->add('username',TextType::Class, array('attr'=>array('class'=>'form-control')))
            ->add('email',TextType::Class, array('attr'=>array('class'=>'form-control')))
            ->add('password',TextType::Class, array('attr'=>array('class'=>'form-control')))
            ->add('save',SubmitType::Class, array('label'=>'save', 'attr'=> array('class' =>'btn btn-primary')))
            ->getForm();
        $form->handleRequest($request);
        if ($form->isSubmitted()&&$form->isValid()) {
            $username = $form['username']->getData();
            $email = $form['email']->getData();
            $password = $form['password']->getData();

            $em = $this->getDoctrine()->getManager();
            $user = $em->getRepository('AppBundle:Member')->find($id);

            $user->setUsername($username);
            $user->setEmail($email);
            $user->setPassword($password);

            $em->flush();
            $this->addFlash('message','User saved');
            return $this->redirectToRoute('view_user_route');
        }

        return $this->render("users/updateuser.html.twig",[
            'form' => $form->createView()
        ] );
    }


    /**
     * @Route("/user/view/{id}", name="show_user_route")
     */
    public function viewOneUserAction($id, Request $request){
        return $this->render("users/viewuser.html.twig");
    }

    /**
     * @Route("/user/deleteuser/{id}", name="delete_user_route")
     */
    public function deleteUserAction($id, Request $request){
        $em = $this->getDoctrine()->getManager();
        $user = $em->getRepository('AppBundle:Member')->find($id);
        $em->remove($user);
        $em->flush();
        $this->addFlash('message','User deleted');
        return $this->redirectToRoute('view_user_route');
    }

}
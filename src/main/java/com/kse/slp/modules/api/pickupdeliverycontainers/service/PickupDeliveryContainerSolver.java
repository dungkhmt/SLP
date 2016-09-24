package com.kse.ezRoutingAPI.pickupdeliverycontainers.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

import localsearch.domainspecific.vehiclerouting.vrp.ConstraintSystemVR;
import localsearch.domainspecific.vehiclerouting.vrp.IConstraintVR;
import localsearch.domainspecific.vehiclerouting.vrp.IFunctionVR;
import localsearch.domainspecific.vehiclerouting.vrp.VRManager;
import localsearch.domainspecific.vehiclerouting.vrp.VarRoutesVR;
import localsearch.domainspecific.vehiclerouting.vrp.constraints.Implicate;
import localsearch.domainspecific.vehiclerouting.vrp.constraints.eq.Eq;
import localsearch.domainspecific.vehiclerouting.vrp.constraints.leq.Leq;
import localsearch.domainspecific.vehiclerouting.vrp.constraints.timewindows.CEarliestArrivalTimeVR;
import localsearch.domainspecific.vehiclerouting.vrp.entities.ArcWeightsManager;
import localsearch.domainspecific.vehiclerouting.vrp.entities.NodeWeightsManager;
import localsearch.domainspecific.vehiclerouting.vrp.entities.Point;
import localsearch.domainspecific.vehiclerouting.vrp.functions.AccumulatedEdgeWeightsOnPathVR;
import localsearch.domainspecific.vehiclerouting.vrp.functions.AccumulatedNodeWeightsOnPathVR;
import localsearch.domainspecific.vehiclerouting.vrp.functions.ConstraintViolationsVR;
import localsearch.domainspecific.vehiclerouting.vrp.functions.IndexOnRoute;
import localsearch.domainspecific.vehiclerouting.vrp.functions.LexMultiFunctions;
import localsearch.domainspecific.vehiclerouting.vrp.functions.RouteIndex;
import localsearch.domainspecific.vehiclerouting.vrp.functions.TotalCostVR;
import localsearch.domainspecific.vehiclerouting.vrp.invariants.AccumulatedWeightEdgesVR;
import localsearch.domainspecific.vehiclerouting.vrp.invariants.AccumulatedWeightNodesVR;
import localsearch.domainspecific.vehiclerouting.vrp.invariants.EarliestArrivalTimeVR;
import localsearch.domainspecific.vehiclerouting.vrp.neighborhoodexploration.GreedyCrossExchangeMoveExplorer;
import localsearch.domainspecific.vehiclerouting.vrp.neighborhoodexploration.GreedyKPointsMoveExplorer;
import localsearch.domainspecific.vehiclerouting.vrp.neighborhoodexploration.GreedyOnePointMoveExplorer;
import localsearch.domainspecific.vehiclerouting.vrp.neighborhoodexploration.GreedyOrOptMove1Explorer;
import localsearch.domainspecific.vehiclerouting.vrp.neighborhoodexploration.GreedyOrOptMove2Explorer;
import localsearch.domainspecific.vehiclerouting.vrp.neighborhoodexploration.GreedyThreeOptMove1Explorer;
import localsearch.domainspecific.vehiclerouting.vrp.neighborhoodexploration.GreedyThreeOptMove2Explorer;
import localsearch.domainspecific.vehiclerouting.vrp.neighborhoodexploration.GreedyThreeOptMove3Explorer;
import localsearch.domainspecific.vehiclerouting.vrp.neighborhoodexploration.GreedyThreeOptMove4Explorer;
import localsearch.domainspecific.vehiclerouting.vrp.neighborhoodexploration.GreedyThreeOptMove5Explorer;
import localsearch.domainspecific.vehiclerouting.vrp.neighborhoodexploration.GreedyThreeOptMove6Explorer;
import localsearch.domainspecific.vehiclerouting.vrp.neighborhoodexploration.GreedyThreeOptMove7Explorer;
import localsearch.domainspecific.vehiclerouting.vrp.neighborhoodexploration.GreedyThreeOptMove8Explorer;
import localsearch.domainspecific.vehiclerouting.vrp.neighborhoodexploration.GreedyTwoOptMove1Explorer;
import localsearch.domainspecific.vehiclerouting.vrp.neighborhoodexploration.GreedyTwoOptMove2Explorer;
import localsearch.domainspecific.vehiclerouting.vrp.neighborhoodexploration.GreedyTwoOptMove3Explorer;
import localsearch.domainspecific.vehiclerouting.vrp.neighborhoodexploration.GreedyTwoOptMove4Explorer;
import localsearch.domainspecific.vehiclerouting.vrp.neighborhoodexploration.GreedyTwoOptMove5Explorer;
import localsearch.domainspecific.vehiclerouting.vrp.neighborhoodexploration.GreedyTwoOptMove6Explorer;
import localsearch.domainspecific.vehiclerouting.vrp.neighborhoodexploration.GreedyTwoOptMove7Explorer;
import localsearch.domainspecific.vehiclerouting.vrp.neighborhoodexploration.GreedyTwoOptMove8Explorer;
import localsearch.domainspecific.vehiclerouting.vrp.neighborhoodexploration.INeighborhoodExplorer;
import localsearch.domainspecific.vehiclerouting.vrp.search.GenericLocalSearch;

import com.kse.ezRoutingAPI.pickupdeliverycontainers.model.PickupDeliveryRequest;
import com.kse.ezRoutingAPI.pickupdeliverycontainers.model.PickupDeliverySolution;
import com.kse.ezRoutingAPI.pickupdeliverycontainers.model.Truck;

class ContainerSearch extends GenericLocalSearch{
	private PickupDeliveryRequest[] req;
	private HashMap<PickupDeliveryRequest, Point> mReq2Pickup;
	private HashMap<PickupDeliveryRequest, Point> mReq2Delivery;
	
	public ContainerSearch(VRManager mgr, PickupDeliveryRequest[] req, HashMap<PickupDeliveryRequest, Point> mReq2Pickup, 
			HashMap<PickupDeliveryRequest, Point> mReq2Delivery){
		super(mgr);
		this.req = req;
		this.mReq2Pickup = mReq2Pickup;
		this.mReq2Delivery = mReq2Delivery;
		
	}
	
	public void generateInitialSolution(){
		for(int i = 0; i < req.length; i++){
			Point s = XR.startPoint(i+1);
			Point pickup = mReq2Pickup.get(req[i]);
			Point delivery = mReq2Delivery.get(req[i]);
			mgr.performAddOnePoint(pickup, s);
			mgr.performAddOnePoint(delivery, pickup);
		}
	}
	
}
public class PickupDeliveryContainerSolver {
	
	PickupDeliveryRequest[] req;// = input.getRequests();
	Truck[] trucks;// = input.getTrucks();

	ArrayList<Point> startPoints;// = new ArrayList<Point>();
	ArrayList<Point> endPoints;// = new ArrayList<Point>();
	ArrayList<Point> pickupPoints;// = new ArrayList<Point>();
	ArrayList<Point> deliveryPoints;// = new ArrayList<Point>();
	ArrayList<Point> allPoints;// = new ArrayList<Point>();
	ArrayList<Point> clientPoints;// = new ArrayList<Point>();
	
	HashMap<Point, PickupDeliveryRequest> mPoint2Request;// = new HashMap<Point, PickupDeliveryRequest>();
	HashMap<PickupDeliveryRequest, Point> mReq2Pickup;// = new HashMap<PickupDeliveryRequest, Point>();
	HashMap<PickupDeliveryRequest, Point> mReq2Delivery;// = new HashMap<PickupDeliveryRequest, Point>();
	HashMap<Point, Truck> mPoint2Truck;// = new HashMap<Point, Truck>();

	ArcWeightsManager distances;// = new ArcWeightsManager(allPoints);
	ArcWeightsManager travelTimes;// = new ArcWeightsManager(allPoints);
	NodeWeightsManager demand;// = new NodeWeightsManager(allPoints);

	HashMap<Point, Integer> earliestAllowedArrivalTime;// = new HashMap<Point, Integer>();
	HashMap<Point, Integer> serviceDuration;// = new HashMap<Point, Integer>();
	HashMap<Point, Integer> latestAllowedArrivalTime;// = new HashMap<Point, Integer>();

	// modelling
	VRManager mgr;// = new VRManager();
	VarRoutesVR XR;// = new VarRoutesVR(mgr);
	ConstraintSystemVR CS;// = new ConstraintSystemVR(mgr);
	AccumulatedWeightEdgesVR awe;// = new AccumulatedWeightEdgesVR(XR, distances);
	AccumulatedWeightNodesVR awn;// = new AccumulatedWeightNodesVR(XR, demand);
	AccumulatedWeightEdgesVR arrivalTime;// = new AccumulatedWeightEdgesVR(XR,	travelTimes);

	HashMap<Point, IFunctionVR> accDemand;// = new HashMap<Point, IFunctionVR>();
	HashMap<Point, IFunctionVR> accDistance;// = new HashMap<Point, IFunctionVR>();
	HashMap<Point, IFunctionVR> routeOfPoint;// = new HashMap<Point, IFunctionVR>();
	HashMap<Point, IFunctionVR> indexOfPointOnRoute;// = new HashMap<Point, IFunctionVR>();


	EarliestArrivalTimeVR eat;
	IFunctionVR obj;// = new TotalCostVR(XR, distances);
	LexMultiFunctions F;// = new LexMultiFunctions();

	public void computeSolution(){
		// model
		mgr = new VRManager();
		XR = new VarRoutesVR(mgr);
		CS = new ConstraintSystemVR(mgr);

		for (int i = 0; i < startPoints.size(); i++) {
			XR.addRoute(startPoints.get(i), endPoints.get(i));
		}

		for (int i = 0; i < pickupPoints.size(); i++) {
			XR.addClientPoint(pickupPoints.get(i));
			XR.addClientPoint(deliveryPoints.get(i));
		}

		int K = XR.getNbRoutes();

		awe = new AccumulatedWeightEdgesVR(XR, distances);
		awn = new AccumulatedWeightNodesVR(XR, demand);
		arrivalTime = new AccumulatedWeightEdgesVR(XR,
				travelTimes);

		for (int k = 1; k <= XR.getNbRoutes(); k++) {
			awe.setAccumulatedWeightStartPoint(k, 0);
			arrivalTime.setAccumulatedWeightStartPoint(k, 0);// start time point of vehicle k
			awn.setAccumulatedWeightStartPoint(k, 0);
		}
		accDemand = new HashMap<Point, IFunctionVR>();
		accDistance = new HashMap<Point, IFunctionVR>();
		for (Point v : allPoints) {
			IFunctionVR dv = new AccumulatedNodeWeightsOnPathVR(awn, v);
			accDemand.put(v, dv);

			IFunctionVR disv = new AccumulatedEdgeWeightsOnPathVR(awe, v);
			accDistance.put(v, disv);

			IFunctionVR idR = new RouteIndex(XR, v);
			for (int k = 1; k <= K; k++) {

				IConstraintVR c1 = new Eq(idR, k);
				IConstraintVR c2 = new Leq(dv,
						trucks[k - 1].getCapacity());
				CS.post(new Implicate(c1, c2));
			}
		}
		HashMap<Point, IFunctionVR> routeOfPoint = new HashMap<Point, IFunctionVR>();
		HashMap<Point, IFunctionVR> indexOfPointOnRoute = new HashMap<Point, IFunctionVR>();

		for (int i = 0; i < req.length; i++) {

			Point pickup = mReq2Pickup.get(req[i]);
			Point delivery = mReq2Delivery.get(req[i]);

			IFunctionVR rp = new RouteIndex(XR, pickup);
			IFunctionVR rd = new RouteIndex(XR, delivery);
			routeOfPoint.put(pickup, rp);
			routeOfPoint.put(delivery, rd);
			CS.post(new Eq(rp, rd));

			IFunctionVR ipickup = new IndexOnRoute(XR, pickup);
			IFunctionVR idelivery = new IndexOnRoute(XR, delivery);
			indexOfPointOnRoute.put(pickup, ipickup);
			indexOfPointOnRoute.put(delivery, idelivery);
			CS.post(new Leq(ipickup, idelivery));

		}

	
		
		eat = new EarliestArrivalTimeVR(XR, travelTimes,
				earliestAllowedArrivalTime, serviceDuration);

		CEarliestArrivalTimeVR twCtrs = new CEarliestArrivalTimeVR(eat,
				latestAllowedArrivalTime);
		CS.post(twCtrs);

		obj = new TotalCostVR(XR, distances);
		F = new LexMultiFunctions();
		F.add(new ConstraintViolationsVR(CS));
		F.add(obj);

		mgr.close();

		/*
		Point last = XR.startPoint(1);
		int I = req.length/2;
		for(int i = 0; i < I; i++){
			Point p = mReq2Pickup.get(req[i]);
			Point d = mReq2Delivery.get(req[i]);
			mgr.performAddOnePoint(p, last);
			last = p;
			mgr.performAddOnePoint(d, last);
			last = d;
		}
		last = XR.startPoint(2);
		for(int i = I; i < req.length; i++){
			Point p = mReq2Pickup.get(req[i]);
			Point d = mReq2Delivery.get(req[i]);
			mgr.performAddOnePoint(p, last);
			last = p;
			mgr.performAddOnePoint(d, last);
			last = d;
		}
		System.out.println("XR = " + XR.toString() + ", F = " + F.getValues().toString());
		//for(Point p: allPoints){
		//	System.out.println("earliestArrivalTime = " + eat.getEarliestArrivalTime(p) + ", latestAllowed = " + latestAllowedArrivalTime.get(p));
		//}
		
		
		if(true)return;
		*/
		
		/*
		 * XR.setRandom(); for(Point p: requestPoints){
		 * System.out.println("routeOfPoint " + p.ID + " = " +
		 * routeOfPoint.get(p).getValue() + ", index of point " + p.ID + " = " +
		 * indexOfPointOnRoute.get(p).getValue()); } System.out.println("XR = "
		 * + XR.toString() + ", F = " + F.getValues().toString());
		 */

		ArrayList<INeighborhoodExplorer> NE = new ArrayList<INeighborhoodExplorer>();
		/*
		NE.add(new GreedyOnePointMoveExplorer(XR, F));

		
		NE.add(new GreedyOrOptMove1Explorer(XR, F));
		NE.add(new GreedyOrOptMove2Explorer(XR, F));
		NE.add(new GreedyThreeOptMove1Explorer(XR, F));
		NE.add(new GreedyThreeOptMove2Explorer(XR, F));
		NE.add(new GreedyThreeOptMove3Explorer(XR, F));
		NE.add(new GreedyThreeOptMove4Explorer(XR, F));
		NE.add(new GreedyThreeOptMove5Explorer(XR, F));
		NE.add(new GreedyThreeOptMove6Explorer(XR, F));
		NE.add(new GreedyThreeOptMove7Explorer(XR, F));
		NE.add(new GreedyThreeOptMove8Explorer(XR, F));
		NE.add(new GreedyTwoOptMove1Explorer(XR, F));
		NE.add(new GreedyTwoOptMove2Explorer(XR, F));
		NE.add(new GreedyTwoOptMove3Explorer(XR, F));
		NE.add(new GreedyTwoOptMove4Explorer(XR, F));
		NE.add(new GreedyTwoOptMove5Explorer(XR, F));
		NE.add(new GreedyTwoOptMove6Explorer(XR, F));
		NE.add(new GreedyTwoOptMove7Explorer(XR, F));
		NE.add(new GreedyTwoOptMove8Explorer(XR, F));
		// NE.add(new GreedyTwoPointsMoveExplorer(XR, F));
		NE.add(new GreedyCrossExchangeMoveExplorer(XR, F));
		// NE.add(new GreedyAddOnePointMoveExplorer(XR, F));
		*/
		
		HashSet<Point> mandatory = new HashSet<Point>();
		for(Point p: clientPoints) mandatory.add(p);
		
		NE.add(new GreedyKPointsMoveExplorer(XR, F, 2, mandatory));

		//GenericLocalSearch se = new GenericLocalSearch(mgr);
		ContainerSearch se = new ContainerSearch(mgr, req, mReq2Pickup, mReq2Delivery);
		se.setNeighborhoodExplorer(NE);
		se.setObjectiveFunction(F);
		
		se.setMaxStable(50);

		se.search(5, 5);

		

		for(int k = 1; k <= XR.getNbRoutes(); k++){
			Point s=XR.startPoint(k);
			Point t=XR.endPoint(k);
			System.out.println("truck[" + k + "] early start = " + earliestAllowedArrivalTime.get(s) + ", late start = " + latestAllowedArrivalTime.get(s)
					+ ", early end = " + earliestAllowedArrivalTime.get(t) + ", late end = " + latestAllowedArrivalTime.get(t));
			
		}
		for(int i = 0; i < req.length; i++){
			Point pickup = mReq2Pickup.get(req[i]);
			Point delivery = mReq2Delivery.get(req[i]);
			System.out.println("Req[" + i + "] = pickup " + req[i].getPickupAddress() + "," + req[i].getPickupDateTime() + " time windows = " + earliestAllowedArrivalTime.get(pickup) + " --> " + latestAllowedArrivalTime.get(pickup));
			System.out.println("Req[" + i + "] = delivery " + req[i].getDeliveryAddress() + "," + req[i].getDeliveryDateTime() + " time windows = " + earliestAllowedArrivalTime.get(delivery) + " --> " + latestAllowedArrivalTime.get(delivery));
			System.out.println("Req[" + i + "] traveltime = " + travelTimes.getDistance(pickup, delivery));
		}
		
		for(int k = 1; k <= XR.getNbRoutes(); k++){
			System.out.println("Route[" + k + "]:");
			Point p;
			for(p = XR.startPoint(k); p != XR.endPoint(k); p = XR.next(p)){
				System.out.println("Point " + p.ID + ": earliestArrivalTime = " + eat.getEarliestArrivalTime(p) + ", latestAllowed = " + latestAllowedArrivalTime.get(p) + ", travel time = " + travelTimes.getDistance(p, XR.next(p)));
			}
			System.out.println("Point " + p.ID + ": earliestArrivalTime = " + eat.getEarliestArrivalTime(p) + ", latestAllowed = " + latestAllowedArrivalTime.get(p));
		}
		
	}
}
